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

import com.bumptech.glide.Glide;
import com.tawan.java.R;
import com.tawan.java.data.remote.QumparanResource;
import com.tawan.java.data.local.MyPreference;
import com.tawan.java.data.remote.reqres.menu.MenuTawanResponsekt;
import com.tawan.java.data.remote.reqres.orderitem.OrderItemResponse;
import com.tawan.java.databinding.ActivityHomeTawanBinding;
import com.tawan.java.databinding.BottomSheetAddMenuBinding;
import com.tawan.java.ui.NavdrawContainerActivity;
import com.tawan.java.ui.home.MainTaskAdapter;

import kotlin.Lazy;

public class HomeTawanActivity extends AppCompatActivity {

    ActivityHomeTawanBinding binding;
    MainTaskAdapter adapter;
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

        adapter = new MainTaskAdapter();
        menuAdapter = new MenuAdapter();

        binding.greetingName.setText(getUserName() + "-"+getUserId());

        initView();
        initData();
    }

    private void initData() {
        getMenuTawan();

        homeViewModel.getValue().getCheckCartLiveData().observe(this, rr -> {
            if (rr instanceof QumparanResource.Loading) {
                showToast("Loading");
                showLoadingOnSheetCheckout(true);
            }
            if (rr instanceof QumparanResource.Success) {
                showLoadingOnSheetCheckout(false);
                setupLayoutBottomSheet(rr.getData());
            }
            if (rr instanceof QumparanResource.Error) {
                showLoadingOnSheetCheckout(false);
            }
        });

        homeViewModel.getValue().getMenuLiveData().observe(this, rr -> {
            if (rr instanceof QumparanResource.Loading) {
                showToast("Loading");
                showLoading(true);
            }
            if (rr instanceof QumparanResource.Success) {
                showLoading(false);
                setupMenuData(rr.getData());
            }
            if (rr instanceof QumparanResource.Error) {
                showToast(rr.getMessage());
                showLoading(false);
            }
        });

    }

    private void setupLayoutBottomSheet(OrderItemResponse data) {
        if (data != null) {
            BottomSheetAddMenuBinding v = binding.sheetAddOrder;
            v.etJumlah.setText(String.valueOf(data.getQuantity()));
            v.etCatatan.setText(data.getNotes());
        } else {

        }
    }

    private void setupMenuData(MenuTawanResponsekt data) {
        menuAdapter.setWithNewData(data);
        menuAdapter.notifyDataSetChanged();
        showToast(String.valueOf(menuAdapter.getItemCount()));
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


        menuAdapter.setupAdapterInterface(model -> {
            showToast(model.getName() + " " + model.getId());
            showAddToCart(model);
            setupAddtCart(model);
        });
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
            boolean isError = false;

            if (view.etJumlah.getText().toString().isEmpty()) {
                isError = true;
                showToast("Jumlah Minimum 1");
            }

            if (Integer.parseInt(view.etJumlah.getText().toString()) < 1) {
                isError = true;
                showToast("Jumlah Minimum 1");
            }

            if (isError) {
                showToast("Perbaiki Inputan");
            } else {
                showToast("");
            }

        });

        view.btnDeleteFromOrder.setOnClickListener(view12 -> {
            showToast("Menghapus " + model.getName() + " ke Pesanan");
        });
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
}