package com.tawan.java.ui.hometawan;

import static org.koin.java.KoinJavaComponent.inject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tawan.java.data.remote.QumparanResource;
import com.tawan.java.data.local.MyPreference;
import com.tawan.java.data.remote.reqres.menu.MenuTawanResponsekt;
import com.tawan.java.databinding.ActivityHomeTawanBinding;
import com.tawan.java.ui.NavdrawContainerActivity;
import com.tawan.java.ui.home.MainTaskAdapter;

import kotlin.Lazy;

public class HomeTawanActivity extends AppCompatActivity {


    ActivityHomeTawanBinding binding;
    MainTaskAdapter adapter;
    MenuAdapter menuAdapter;
    private Lazy<HomeViewModel> homeViewModel = inject(HomeViewModel.class);


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeTawanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new MainTaskAdapter();
        menuAdapter = new MenuAdapter();

        initView();
        initData();
    }

    private void initData() {
        getMenuTawan();

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

    private void initView() {

        binding.btnLogout.setOnClickListener(view -> {
            new MyPreference(this).clearPreferences();
            finish();
            startActivity(new Intent(this, NavdrawContainerActivity.class));
        });


        binding.rv.setAdapter(menuAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setHasFixedSize(true);


        menuAdapter.setupAdapterInterface(model -> {
            showToast(model.getName() + " " + model.getId());
        });

    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}