package com.tawan.java.ui.checkout;

import static org.koin.java.KoinJavaComponent.inject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.tawan.java.R;
import com.tawan.java.data.local.MyPreference;
import com.tawan.java.data.remote.QumparanResource;
import com.tawan.java.data.remote.reqres.GeneralApiResponse;
import com.tawan.java.data.remote.reqres.cart.UserCartResponsekt;
import com.tawan.java.data.remote.reqres.cart.UserCartResponsekt.ResData.OrderedItem;
import com.tawan.java.data.remote.reqres.invoice.InvoicePayload;
import com.tawan.java.data.remote.reqres.invoice.InvoicePayloadkt;
import com.tawan.java.data.remote.reqres.menu.MenuTawanResponsekt;
import com.tawan.java.data.remote.reqres.orderitem.OrderItemPayload;
import com.tawan.java.data.remote.reqres.orderitem.OrderItemResponse;
import com.tawan.java.databinding.ActivityCheckoutBinding;
import com.tawan.java.databinding.BottomSheetAddMenuBinding;
import com.tawan.java.ui.history.HistoryActivity;
import com.tawan.java.ui.hometawan.HomeViewModel;
import com.tawan.java.utils.UtilSnackbar;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.List;

import kotlin.Lazy;

public class CheckoutActivity extends AppCompatActivity {

    ActivityCheckoutBinding binding;
    CheckoutItemAdapter cartAdapter;

    List<String> cart_ids;

    private Lazy<HomeViewModel> homeViewModel = inject(HomeViewModel.class);
    boolean isAnyBottomSheetVisible = false;

    @Override
    protected void onResume() {
        super.onResume();
        getMenuTawan();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMenuTawan();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cartAdapter = new CheckoutItemAdapter();

        initView();
        initData();
        fetchUserCart();
    }

    private void initData() {
        homeViewModel.getValue().getUserCartLiveData().observe(this, rr -> {
            if (rr instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (rr instanceof QumparanResource.Success) {
                showLoading(false);
                setupWholeCartUI(rr.getData().getResData());
            }
            if (rr instanceof QumparanResource.Error) {
                showToast(rr.getMessage());
                showLoading(false);
            }
        });


        homeViewModel.getValue().getUpdateCartLiveData().observe(this, res -> {
            if (res instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (res instanceof QumparanResource.Success) {
                showLoading(false);
                fetchUserCart();
                UtilSnackbar.showSnakbarSuccess(this, binding.getRoot(), "Berhasil Mengupdate Keranjang");
            }
            if (res instanceof QumparanResource.Error) {
                fetchUserCart();
                showLoading(false);
                UtilSnackbar.showSnakbarError(this, binding.getRoot(), res.getMessage());
            }
        });

        homeViewModel.getValue().getSaveInvoiceLiveData().observe(this, res -> {
            if (res instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (res instanceof QumparanResource.Success) {
                showLoading(false);
                finish();
                startActivity(new Intent(this, HistoryActivity.class));
                UtilSnackbar.showSnakbarSuccess(this, binding.getRoot(), "Pesanan Berhasil Diinput, Track Pesanan pada menu riwayat");
            }
            if (res instanceof QumparanResource.Error) {
                showLoading(false);
                UtilSnackbar.showSnakbarError(this, binding.getRoot(), res.getMessage());
            }
        });

        homeViewModel.getValue().getDeleteCartLiveData().observe(this, res -> {
            if (res instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (res instanceof QumparanResource.Success) {
                fetchUserCart();
                showLoading(false);
                UtilSnackbar.showSnakbarSuccess(this, binding.getRoot(), "Berhasil Menghapus Item dari Keranjang");
            }
            if (res instanceof QumparanResource.Error) {
                showLoading(false);
                UtilSnackbar.showSnakbarError(this, binding.getRoot(), res.getMessage());
            }
        });
    }

    private void setupWholeCartUI(UserCartResponsekt.ResData rr) {
        binding.tvTotalHarga.setText("Total Harga : " + rr.getTotalPriceRupiahFormat());
        binding.tvTotalHargaRaw.setText(String.valueOf((int) rr.getTotalPrice()));
        binding.tvTotalQuantity.setText("Total Pesanan : " + String.valueOf(rr.getTotalQuantity()) + " pcs");

        StringBuilder ordered = new StringBuilder();
        int count = 0;

        cart_ids = new ArrayList<>();
        cart_ids.clear();

        for (OrderedItem item : rr.getOrderedItem()) {
            cart_ids.add(String.valueOf(item.getId()));
            ordered.append(String.valueOf(count + 1) + ". " + item.getMenuName()).append("\n");
            count++;
        }

        cartAdapter.setWithNewData(rr.getOrderedItem());
        cartAdapter.notifyDataSetChanged();

        binding.tvList.setText(ordered.toString());
    }

    private void fetchUserCart() {
        homeViewModel.getValue().getUserCart(getUserId());
    }


    private void proceedUpdateCart(OrderedItem data, BottomSheetAddMenuBinding v) {
        int quantity = Integer.parseInt(v.etJumlah.getText().toString());
        String notes = v.etCatatan.getText().toString();
        homeViewModel.getValue().updateCart(new OrderItemPayload(
                data.getCreatedAt(),
                data.getId(),
                data.getIdMenu(),
                data.getIdResto(),
                Integer.parseInt(getUserId()),
                notes,
                data.getPrice(),
                data.getPriceMultiplied(),
                quantity,
                ""
        ));
    }


    private void getMenuTawan() {
        homeViewModel.getValue().getMenu();
    }


    private void showLoading(boolean b) {
    }

    private void showLoadingOnSheetCheckout(boolean b) {
        BottomSheetAddMenuBinding v = binding.sheetAddOrder;
        if (b) {
            v.loading.setVisibility(View.VISIBLE);
        } else {
            v.loading.setVisibility(View.GONE);
        }
    }

    private void initView() {
        binding.rv.setAdapter(cartAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));

        binding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCheckoutAction();
            }
        });

        cartAdapter.setupAdapterInterface(new CheckoutItemAdapter.ItemInterface() {
            @Override
            public void onEdit(@NonNull OrderedItem model) {
                showAddToCart(model);
            }

            @Override
            public void onclick(@NonNull OrderedItem model) {
                showAddToCart(model);
            }

            @Override
            public void onDelete(@NonNull OrderedItem model) {
                new LovelyStandardDialog(CheckoutActivity.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.red)
                        .setButtonsColorRes(R.color.domain_blue)
                        .setNegativeButtonColor(getResources().getColor(R.color.black))
                        .setTitle("Anda Yakin ?")
                        .setMessage("Pesanan ini akan dihapus dari keranjang")
                        .setPositiveButton("Batal", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setNegativeButton("Hapus", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                homeViewModel.getValue().deleteCart(String.valueOf(model.getId()));
                            }
                        })
                        .show();

            }
        });
    }

    private void btnCheckoutAction() {

        boolean isError = false;
        String notes = binding.textFieldNotes.getEditText().getText().toString();
        String payment = binding.textFieldPayment.getEditText().getText().toString();
        String address = binding.textFieldAlamat.getEditText().getText().toString();


        if (payment.isEmpty()) {
            isError = true;
            showToast("Payment tidak boleh kosong");
        }

        if (!payment.isEmpty()) {
            if (isNumber(payment)) {
                if (Integer.parseInt(payment) < Integer.parseInt(binding.tvTotalHargaRaw.getText().toString())) {
                    new LovelyInfoDialog(this)
                            .setTopColorRes(R.color.red)
                            //This will add Don't show again checkbox to the dialog. You can pass any ID as argument
                            .setTitle("Perhatian")
                            .setMessage("Pembayaran tidak boleh kurang dari harga total")
                            .show();
                    isError = true;
                }
            }
        }

        if (!isError) {
            InvoicePayloadkt payload = new InvoicePayloadkt(
                    address,
                    cart_ids,
                    new MyPreference(CheckoutActivity.this).getRestoID(),
                    getUserId(),
                    "",
                    "",
                    notes
            );
            homeViewModel.getValue().saveInvoice(payload);

        } else {
            new LovelyInfoDialog(this)
                    .setTopColorRes(R.color.red)
                    .setTopTitle("Perhatian")
                    .setTopTitleColor(R.color.white)
                    .setMessage("Perbaiki Inputan Anda Terlebih Dahulu")
                    .show();
        }

    }

    private boolean isCartInputError() {
        boolean isError = false;
        BottomSheetAddMenuBinding view = binding.sheetAddOrder;
        if (view.etJumlah.getText().toString().isEmpty() || view.etJumlah.getText().toString().equals("")) {
            isError = true;
            showToast("Jumlah Minimum 1");
        }
        if (isNumber(view.etJumlah.getText().toString())) {
            if (Integer.parseInt(view.etJumlah.getText().toString()) < 1) {
                isError = true;
                showToast("Jumlah Minimum 1");
            }
        }

        return isError;
    }

    private String getUserId() {
        return new MyPreference(this).getUserID();
    }

    private String getUserName() {
        return new MyPreference(this).getUserName();
    }

    private void showAddToCart(OrderedItem model) {
        isAnyBottomSheetVisible = true;
        BottomSheetAddMenuBinding v = binding.sheetAddOrder;
        v.root.setVisibility(View.VISIBLE);
        v.root.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_appear_300ms));
        v.etCatatan.setText(model.getNotes());
        v.tvMain.setText(model.getMenuName());
        v.tvDescription.setText(model.getMenu().getDesc());
        v.tvPrice.setText(String.valueOf(model.getMenu().getPrice()));
        v.etJumlah.setText(String.valueOf(model.getQuantity()));

        Glide.with(binding.getRoot())
                .load(model.getMenu().getThumbnailUrl())
                .skipMemoryCache(true)
                .dontAnimate()
                .centerCrop()
                .into(v.ivMainImage);

        v.btnDeleteFromOrder.setVisibility(View.GONE);
        v.btnAddToOrder.setText("Simpan Perubahan");
        v.btnAddToOrder.setOnClickListener(view -> {
            closeCheckoutBottomSheet();
            showToast("Mengupdate Data");
            proceedUpdateCart(model, v);
        });
        binding.sheetAddOrder.btnClose.setOnClickListener(view -> {
            closeCheckoutBottomSheet();
        });
    }

    private void closeCheckoutBottomSheet() {
        isAnyBottomSheetVisible = false;
        binding.sheetAddOrder.root.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_gone_300ms));
        binding.sheetAddOrder.root.setVisibility(View.GONE);
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        if (isAnyBottomSheetVisible) {
            closeCheckoutBottomSheet();
        } else {
            super.onBackPressed();
        }
    }

    public boolean isNumber(String text) {
        try {
            int intValue = Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}