package com.example.mypractice.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mypractice.model.Item
import com.example.mypractice.network.RemoteApi
import io.reactivex.disposables.CompositeDisposable


class UsersDataSourceFactory(
    private val remoteApi: RemoteApi,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Item>() {

    val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Int, Item> {
        val usersDataSource = UsersDataSource(remoteApi, compositeDisposable)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }
}