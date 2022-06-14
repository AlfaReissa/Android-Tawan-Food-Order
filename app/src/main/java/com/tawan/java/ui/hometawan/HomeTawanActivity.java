package com.tawan.java.ui.hometawan;

import static org.koin.java.KoinJavaComponent.inject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tawan.java.R;
import com.tawan.java.data.remote.QumparanResource;
import com.tawan.java.data.local.MyPreference;
import com.tawan.java.data.remote.reqres.GeneralApiResponse;
import com.tawan.java.data.remote.reqres.cart.UserCartResponsekt;
import com.tawan.java.data.remote.reqres.cart.UserCartResponsekt.ResData.OrderedItem;
import com.tawan.java.data.remote.reqres.menu.MenuTawanResponsekt;
import com.tawan.java.data.remote.reqres.orderitem.OrderItemPayload;
import com.tawan.java.data.remote.reqres.orderitem.OrderItemResponse;
import com.tawan.java.databinding.ActivityHomeTawanBinding;
import com.tawan.java.databinding.BottomSheetAddMenuBinding;
import com.tawan.java.ui.NavdrawContainerActivity;
import com.tawan.java.ui.checkout.CheckoutActivity;
import com.tawan.java.utils.UtilSnackbar;

import kotlin.Lazy;

public class HomeTawanActivity extends AppCompatActivity {

    ActivityHomeTawanBinding binding;
    MenuAdapter menuAdapter;
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
        binding = ActivityHomeTawanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        menuAdapter = new MenuAdapter();

        binding.greetingName.setText(getUserName() + "-" + getUserId());
        binding.containerCheckout.setVisibility(View.GONE);

        binding.containerCheckout.setOnClickListener(view -> {
            startActivity(new Intent(this, CheckoutActivity.class));
        });

        initView();
        initData();
    }

    private void initData() {
        getMenuTawan();

        homeViewModel.getValue().getCheckCartLiveData().observe(this, rr -> {
            if (rr instanceof QumparanResource.Loading) {
                showLoadingOnSheetCheckout(true);
            }
            if (rr instanceof QumparanResource.Success) {
                showLoadingOnSheetCheckout(false);
                fetchUserCart();
                setupLayoutBottomSheetOnExist(rr.getData());
            }
            if (rr instanceof QumparanResource.Error) {
                showLoadingOnSheetCheckout(false);
            }
        });

        homeViewModel.getValue().getSaveCartLiveData().observe(this, res -> {
            if (res instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (res instanceof QumparanResource.Success) {
                showLoading(false);
                fetchUserCart();
                UtilSnackbar.showSnakbarSuccess(this, binding.getRoot(), "Berhasil Menambahkan Data");
            }
            if (res instanceof QumparanResource.Error) {
                showLoading(false);
                UtilSnackbar.showSnakbarError(this, binding.getRoot(), res.getMessage());
            }
        });

        homeViewModel.getValue().getUpdateCartLiveData().observe(this, res -> {
            if (res instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (res instanceof QumparanResource.Success) {
                showLoading(false);
                homeViewModel.getValue().getUserCart(getUserId());
                UtilSnackbar.showSnakbarSuccess(this, binding.getRoot(), "Berhasil Mengupdate Keranjang");
            }
            if (res instanceof QumparanResource.Error) {
                showLoading(false);
                UtilSnackbar.showSnakbarError(this, binding.getRoot(), res.getMessage());
            }
        });


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


        homeViewModel.getValue().getDeleteCartLiveData().observe(this, res -> {
            fetchUserCart();
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

        homeViewModel.getValue().getMenuLiveData().observe(this, rr -> {
            if (rr instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (rr instanceof QumparanResource.Success) {
                fetchUserCart();
                showLoading(false);
                setupMenuData(rr.getData());
            }
            if (rr instanceof QumparanResource.Error) {
                showToast(rr.getMessage());
                showLoading(false);
            }
        });
    }

    private void setupWholeCartUI(UserCartResponsekt.ResData rr) {
        binding.tvCheckout.setText(rr.getTotalPriceRupiahFormat());
        StringBuilder ordered = new StringBuilder();
        for(OrderedItem item : rr.getOrderedItem()) {
            ordered.append(item.getMenuName()).append(", ");
        }

        if(rr.getTotalQuantity()==0){
            binding.containerCheckout.setVisibility(View.GONE);
        }else{
            if(binding.containerCheckout.getVisibility() == View.GONE){
                binding.containerCheckout.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_appear_300ms));
            }

            binding.containerCheckout.setVisibility(View.VISIBLE);
        }

        binding.tvItemCheckout.setText(ordered.toString());
    }

    private void fetchUserCart() {
        homeViewModel.getValue().getUserCart(getUserId());
    }


    private void setupLayoutBottomSheetOnExist(OrderItemResponse data) {
        if (data != null) {
            BottomSheetAddMenuBinding v = binding.sheetAddOrder;
            v.etJumlah.setText(String.valueOf(data.getQuantity()));
            v.etCatatan.setText(data.getNotes());
            v.btnDeleteFromOrder.setVisibility(View.VISIBLE);
            v.btnAddToOrder.setText("Ubah Pesanan");

            v.btnDeleteFromOrder.setOnClickListener(view12 -> {
                showToast("Menghapus Pesanan");
                closeCheckoutBottomSheet();
                homeViewModel.getValue().deleteCart(String.valueOf(data.getId()));
            });

            v.btnAddToOrder.setOnClickListener(view1 -> {
                if (isCartInputError()) {
                    showToast("Perbaiki Inputan");
                } else {
                    // add item to cart
                    closeCheckoutBottomSheet();
                    proceedUpdateCart(data, v);
                }
            });

        } else {

        }
    }

    private void proceedUpdateCart(OrderItemResponse data, BottomSheetAddMenuBinding v) {
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

    private void setupMenuData(MenuTawanResponsekt data) {
        menuAdapter.setWithNewData(data);
        menuAdapter.notifyDataSetChanged();
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

        binding.btnLogout.setOnClickListener(view -> {
            new MyPreference(this).clearPreferences();
            finish();
            startActivity(new Intent(this, NavdrawContainerActivity.class));
        });


        binding.rv.setAdapter(menuAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && binding.containerCheckout.getVisibility() == View.VISIBLE) {
//                    binding.containerCheckout.setVisibility(View.GONE);
//                    binding.containerCheckout.setAnimation(AnimationUtils.loadAnimation(
//                            HomeTawanActivity.this, R.anim.bottom_gone_300ms)
//                    );
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    binding.containerCheckout.setVisibility(View.VISIBLE);
//                    binding.containerCheckout.setAnimation(
//                            AnimationUtils.loadAnimation(
//                                    HomeTawanActivity.this, R.anim.bottom_appear_300ms)
//                    );
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        menuAdapter.setupAdapterInterface(model -> {
            showAddToCart(model);
            setupAddtCart(model);
        });
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

    private void setupAddtCart(MenuTawanResponsekt.MenuTawanResponsektItem model) {
        BottomSheetAddMenuBinding view = binding.sheetAddOrder;
        Glide.with(binding.getRoot())
                .load(model.getThumbnailUrl())
                .skipMemoryCache(true)
                .dontAnimate()
                .centerCrop()
                .into(view.ivMainImage);

        view.tvMain.setText(model.getName());
        view.tvDescription.setText(model.getDesc());


        view.btnAddToOrder.setOnClickListener(view1 -> {
            if (isCartInputError()) {
                showToast("Perbaiki Inputan");
            } else {
                // add item to cart
                closeCheckoutBottomSheet();
                proceedSaveCart(model, view);
            }
        });


    }

    private void proceedSaveCart(
            MenuTawanResponsekt.MenuTawanResponsektItem model,
            BottomSheetAddMenuBinding view) {
        homeViewModel.getValue().saveCartResponse(new OrderItemPayload(
                "",
                0,
                model.getId(),
                model.getIdResto(),
                Integer.parseInt(getUserId()),
                view.etCatatan.getText().toString(),
                "",
                0,
                Integer.parseInt(view.etJumlah.getText().toString()),
                ""
        ));
    }

    private String getUserId() {
        return new MyPreference(this).getUserID();
    }

    private String getUserName() {
        return new MyPreference(this).getUserName();
    }

    private void showAddToCart(MenuTawanResponsekt.MenuTawanResponsektItem model) {
        isAnyBottomSheetVisible = true;
        BottomSheetAddMenuBinding v = binding.sheetAddOrder;
        homeViewModel.getValue().checkOrderItem(
                getUserId(),
                String.valueOf(model.getId())
        );
        v.root.setVisibility(View.VISIBLE);
        v.root.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_appear_300ms));
        v.etCatatan.setText("");
        v.etJumlah.setText("");

        v.btnDeleteFromOrder.setVisibility(View.GONE);
        v.btnAddToOrder.setText("Tambah ke Keranjang");

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