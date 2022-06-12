package com.tawan.java.ui.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.tawan.java.databinding.ActivityChangeLanguageBinding;
import com.tawan.java.utils.LocaleHelper;


public class ChangeLanguageActivity extends AppCompatActivity {

    ActivityChangeLanguageBinding binding;

    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityChangeLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // setting up on click listener event over the button
        // in order to change the language with the help of
        // LocaleHelper class
        binding.btnEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LocaleHelper.setLocale(ChangeLanguageActivity.this, "en");
                resources = context.getResources();
                finish();
                startActivity(new Intent(ChangeLanguageActivity.this,ChangeLanguageActivity.class));
            }
        });

        binding.btnIndo.setOnClickListener(view -> {
            context = LocaleHelper.setLocale(ChangeLanguageActivity.this, "in");
            resources = context.getResources();
            finish();
            startActivity(new Intent(ChangeLanguageActivity.this,ChangeLanguageActivity.class));
        });
    }
}