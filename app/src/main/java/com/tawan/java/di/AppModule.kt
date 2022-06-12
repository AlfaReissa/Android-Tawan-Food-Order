package com.tawan.java.di

import com.tawan.java.ui.auth.AuthViewModel
import com.tawan.java.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}

