package com.tawan.java.ui.history;

import static org.koin.java.KoinJavaComponent.inject;

import android.annotation.SuppressLint;
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
import com.tawan.java.data.remote.reqres.cart.UserCartResponsekt;
import com.tawan.java.data.remote.reqres.cart.UserCartResponsekt.ResData.OrderedItem;
import com.tawan.java.data.remote.reqres.invoice.InvoicePayloadkt;
import com.tawan.java.data.remote.reqres.invoice.UserInvoiceResponsekt;
import com.tawan.java.data.remote.reqres.orderitem.OrderItemPayload;
import com.tawan.java.databinding.ActivityCheckoutBinding;
import com.tawan.java.databinding.ActivityHistoryBinding;
import com.tawan.java.databinding.BottomSheetAddMenuBinding;
import com.tawan.java.ui.checkout.CheckoutItemAdapter;
import com.tawan.java.ui.hometawan.HomeViewModel;
import com.tawan.java.utils.UtilSnackbar;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.List;

import kotlin.Lazy;

public class HistoryActivity extends AppCompatActivity {

    ActivityHistoryBinding binding;
    HistoryAdapter cartAdapter;

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
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cartAdapter = new HistoryAdapter();
        initView();
        initData();
        fetchUserCart();
        homeViewModel.getValue().getUserHistoryInvoice(getUserId());
    }

    private void initData() {

        homeViewModel.getValue().getUserInvoiceHistoryLiveData().observe(this, rr -> {
            if (rr instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (rr instanceof QumparanResource.Success) {
                showLoading(false);
                setupHistoryUI(rr.getData());
            }
            if (rr instanceof QumparanResource.Error) {
                showToast(rr.getMessage());
                showLoading(false);
            }
        });
    }

    private void setupHistoryUI(UserInvoiceResponsekt data) {
        cartAdapter.setWithNewData(data);
        cartAdapter.notifyDataSetChanged();
    }

    private void setupWholeCartUI(UserCartResponsekt.ResData rr) {

    }

    private void fetchUserCart() {
        homeViewModel.getValue().getUserCart(getUserId());
    }


    private void getMenuTawan() {
        homeViewModel.getValue().getMenu();
    }

    private void showLoading(boolean b) {
        if(b){

        }else{

        }
    }

    private void initView() {
        binding.rv.setAdapter(cartAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));

        cartAdapter.setAdapterInterface(new HistoryAdapter.ItemInterface() {
            @Override
            public void onclick(@NonNull UserInvoiceResponsekt.UserInvoiceResponsektItem model) {
            }

            @Override
            public void onDelete(@NonNull UserInvoiceResponsekt.UserInvoiceResponsektItem model) {
            }

            @Override
            public void onEdit(@NonNull UserInvoiceResponsekt.UserInvoiceResponsektItem model) {
            }
        });

    }

    private String getUserId() {
        return new MyPreference(this).getUserID();
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}