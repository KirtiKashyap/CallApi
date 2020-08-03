package com.example.mypractice.base

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.mypractice.injection.component.DaggerViewModelInjector
import com.example.mypractice.injection.component.ViewModelInjector
import com.example.mypractice.injection.module.NetworkModule
import com.example.mypractice.viewmodel.UserListViewModel

abstract class BaseViewModel : ViewModel() {

    private var mApplication: Application? = null

    /**
     * Return the application.
     */
    open fun <T : Application?> getApplication(): T {
        return mApplication as T
    }
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