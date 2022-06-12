package com.tawan.java.ui.auth;

import static androidx.navigation.fragment.NavHostFragment.findNavController;
import static org.koin.java.KoinJavaComponent.inject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.henrylabs.qumparan.data.remote.QumparanResource;
import com.tawan.java.R;
import com.tawan.java.data.local.MyPreference;
import com.tawan.java.data.remote.reqres.RegisterResponse.ResData;
import com.tawan.java.data.remote.reqres.RegisterResponse;
import com.tawan.java.databinding.FragmentRegisterTawanBinding;
import com.tawan.java.ui.hometawan.HomeTawanActivity;

import kotlin.Lazy;


public class RegisterTawanFragment extends Fragment {


    private FragmentRegisterTawanBinding binding;
    private Lazy<AuthViewModel> authViewModel = inject(AuthViewModel.class);


    public RegisterTawanFragment() {
        // Required empty public constructor
    }

    public static LoginTawanFragment newInstance(String param1, String param2) {
        LoginTawanFragment fragment = new LoginTawanFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObserver();

        binding.btnLogin.setOnClickListener(viewx -> {
            findNavController(this).navigate(R.id.loginTawanFragment);
        });

        binding.btnRegis.setOnClickListener(viewx -> {
            validateInput();
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void validateInput() {
        String email = binding.textFieldEmail.getEditText().getText().toString();
        String password = binding.textFieldPassword.getEditText().getText().toString();
        String name = binding.textFieldName.getEditText().getText().toString();
        boolean isError = false;

        if (!isValidEmail(email)) {
            isError = true;
            binding.textFieldEmail.setError(getString(R.string.email_format_error));
        }

        if (password != null || password.equals("")) {
            if (password.length() < 8) {
                isError = true;
                binding.textFieldPassword.setError(getString(R.string.minimal_8_karakter));
            }
        }

        if (name != null || name.equals("")) {
            if (password.length() < 8) {
                isError = true;
                binding.textFieldPassword.setError(getString(R.string.minimal_8_karakter));
            }
        }

        if (password == null || password.equals("")) {
            isError = true;
            binding.textFieldPassword.setError(getString(R.string.column_required));
        }

        if (!isError) {
            proceedRegister(
                    name.toString(),
                    email.toString(),
                    password.toString()
            );
        }
    }

    private void proceedRegister(String name,String email, String password) {
        authViewModel.getValue().register(name,email, password);
    }

    private void initObserver() {
        authViewModel.getValue().getRegisterLiveData().observe(getViewLifecycleOwner(), rr -> {
            if (rr instanceof QumparanResource.Loading) {
                showLoading(true);
            }
            if (rr instanceof QumparanResource.Success) {
                showLoading(false);
                showToast(rr.getMessage().toString());
                setupRegist(rr.getData());
            }
            if (rr instanceof QumparanResource.Error) {
                showToast(rr.getMessage().toString());
                showLoading(false);
            }
        });
    }

    private void setupRegist(RegisterResponse data) {
        ResData usr = data.getResData();
        new MyPreference(requireContext()).saveUserID(String.valueOf(usr.getId()));
        new MyPreference(requireContext()).saveUserName(usr.getName());
        new MyPreference(requireContext()).saveUserEmail(usr.getEmail());
        requireActivity().finish();
        startActivity(new Intent(requireContext(), HomeTawanActivity.class));
    }

    private void showLoading(boolean b) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterTawanBinding.inflate(inflater);
        return binding.getRoot();
    }

    private void showToast(String text){
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show();
    }
}