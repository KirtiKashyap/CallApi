package com.example.mypractice.datasource

import androidx.paging.PageKeyedDataSource
import com.example.mypractice.model.Item
import com.example.mypractice.network.RemoteApi
import com.example.mypractice.utils.ApiConstants
import io.reactivex.disposables.CompositeDisposable

class UsersDataSource(
    private val remoteApi: RemoteApi,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Item>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        compositeDisposable.add(
            remoteApi.getData(
                ApiConstants.FIRST_PAGE + 1,
                ApiConstants.PAGE_SIZE,
                ApiConstants.ORDER,
                ApiConstants.SORT,
                ApiConstants.SITE
            ).subscribe({ users ->
                callback.onResult(users.items, null, ApiConstants.FIRST_PAGE + 1)
            }, { throwable -> })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

        compositeDisposable.add(
            remoteApi.getData(
                params.key + 1,
                ApiConstants.PAGE_SIZE,
                ApiConstants.ORDER,
                ApiConstants.SORT,
                ApiConstants.SITE
            ).subscribe({ users ->
                //if(users.hasMore)
                callback.onResult(users.items, params.key + 1)
            }, { throwable -> })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        val key = if (params.key > 1) params.key - 1 else 0
        compositeDisposable.add(
            remoteApi.getData(
                key,
                ApiConstants.PAGE_SIZE,
                ApiConstants.ORDER,
                ApiConstants.SORT,
                ApiConstants.SITE
            ).subscribe({ users ->
                callback.onResult(users.items, key)
            }, { throwable -> })
        )
    }
}