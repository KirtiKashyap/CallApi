package com.example.mypractice.injection.component

import com.example.mypractice.viewmodel.UserListViewModel
import com.example.mypractice.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified UserListViewModel.
     * @param userListViewModel UserListViewModel in which to inject the dependencies
     */
    fun inject(userListViewModel: UserListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}