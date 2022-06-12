package com.tawan.java.utils;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class MyHelper {

    public NavController findNavController(Fragment fragment) {
        return NavHostFragment.findNavController(fragment);
    }

}
