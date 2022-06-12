package com.tawan.java.ui.home;

import static org.koin.java.KoinJavaComponent.inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.henrylabs.qumparan.data.remote.QumparanResource;
import com.tawan.java.R;
import com.tawan.java.customview.BottomSheetTaskFragment;
import com.tawan.java.data.local.MyPreference;
import com.tawan.java.data.remote.reqres.TasksResponse;
import com.tawan.java.databinding.ActivityHomeBinding;
import com.tawan.java.ui.NavdrawContainerActivity;
import com.tawan.java.ui.edittask.EditTaskActivity;
import com.tawan.java.ui.new_task.NewTaskActivity;

import kotlin.Lazy;
import kotlin.Unit;

public class HomeActivity extends AppCompatActivity {


    ActivityHomeBinding binding;
    MainTaskAdapter adapter;
    private Lazy<HomeViewModel> homeViewModel = inject(HomeViewModel.class);


    @Override
    protected void onResume() {
        super.onResume();
        getUserTask();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getUserTask();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new MainTaskAdapter();

        initView();
        initData();
    }

    private void initData() {
        getUserTask();

        homeViewModel.getValue().getSearchLiveData().observe(this, tasksResponseQumparanResource -> {
            if (tasksResponseQumparanResource instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (tasksResponseQumparanResource instanceof QumparanResource.Success) {
                showLoading(false);
                setupData(tasksResponseQumparanResource.getData());
            }
            if (tasksResponseQumparanResource instanceof QumparanResource.Error) {
                showLoading(false);
            }
        });

        homeViewModel.getValue().getDeleteTaskLiveData().observe(this, res -> {
            if (res instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (res instanceof QumparanResource.Success) {
                showLoading(false);
                getUserTask();
                new NewTaskActivity().showToast(this, getString(R.string.data_deleted));
            }
            if (res instanceof QumparanResource.Error) {
                showLoading(false);
                new NewTaskActivity().showToast(this, getString(R.string.data_deleted_fail));
            }
        });
    }

    private void getUserTask() {
        homeViewModel.getValue().getUserTask(
                new MyPreference(this).getUserID()
        );
    }

    private void setupData(TasksResponse data) {
        adapter.setWithNewData(data.getListTask());
        adapter.notifyDataSetChanged();

        if(adapter.getItemCount()>0){
            binding.endpage.setVisibility(View.GONE);
        }else{
            binding.endpage.setVisibility(View.VISIBLE);
        }
    }

    private void showLoading(boolean b) {

    }

    private Unit edit() {
        //do something here
        String id = homeViewModel.getValue().getSelectedId().getValue();
        startActivity(new Intent(this, EditTaskActivity.class).putExtra("taskID", id));
        return Unit.INSTANCE;
    }

    private Unit deleteTask() {
        //do something here
        String id = homeViewModel.getValue().getSelectedId().getValue();
        homeViewModel.getValue().deleteTask(id);
        return Unit.INSTANCE;
    }

    private void initView() {

        binding.logout.setOnClickListener(view -> {
            new MyPreference(this).clearPreferences();
            finish();
            startActivity(new Intent(this, NavdrawContainerActivity.class));
        });

        binding.changeLang.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings");
            startActivity(intent);
        });

        binding.rvOurdoes.setAdapter(adapter);
        binding.rvOurdoes.setLayoutManager(new LinearLayoutManager(this));
        binding.rvOurdoes.setHasFixedSize(true);


        adapter.setAdapterInterface(model -> {
            homeViewModel.getValue().getSelectedId().postValue(String.valueOf(model.getId()));
            BottomSheetTaskFragment.Companion.instance(
                    this::deleteTask, this::edit
            ).show(getSupportFragmentManager(), BottomSheetTaskFragment.Companion.getTAG());

        });


        binding.btnAddNew.setOnClickListener(view -> {
            startActivity(new Intent(this, NewTaskActivity.class));
        });
    }
}