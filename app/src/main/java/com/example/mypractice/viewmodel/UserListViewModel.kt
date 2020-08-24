package com.example.mypractice.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mypractice.base.BaseViewModel
import com.example.mypractice.datasource.UsersDataSource
import com.example.mypractice.datasource.UsersDataSourceFactory
import com.example.mypractice.model.Item
import com.example.mypractice.network.RemoteApi
import com.example.mypractice.utils.ApiConstants
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/** View models manipulates models and read data from models */
class UserListViewModel : BaseViewModel() {
    /**Inject Remote interface*/
    @Inject
    lateinit var remoteApi: RemoteApi

    /** initialize and dispose after use */
    private val compositeDisposable = CompositeDisposable()

    /** Hold the searching username */
    var searchUserName: String? = null

    val userPagedList: LiveData<PagedList<Item>>

    private val liveDataSource: LiveData<UsersDataSource>

    init {
        val sourceFactory = UsersDataSourceFactory(remoteApi, compositeDisposable)

        liveDataSource = sourceFactory.usersDataSourceLiveData

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ApiConstants.PAGE_SIZE)
            .build()

        userPagedList = LivePagedListBuilder(sourceFactory, config)
            .build()

    }

    /** Dispose call from UserListViewModel when this ViewModel is cleared to avoid the memory leak*/
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}