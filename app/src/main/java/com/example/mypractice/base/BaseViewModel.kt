package com.example.mypractice.base

import androidx.lifecycle.ViewModel
import com.example.mypractice.injection.component.DaggerViewModelInjector
import com.example.mypractice.injection.component.ViewModelInjector
import com.example.mypractice.injection.module.NetworkModule
import com.example.mypractice.viewmodel.UserListViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is UserListViewModel -> injector.inject(this)
        }
    }
}