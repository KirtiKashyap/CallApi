package com.example.mypractice.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.mypractice.utils.ApiConstants
import com.example.mypractice.R
import com.example.mypractice.network.RemoteApi
import com.example.mypractice.base.BaseViewModel
import com.example.mypractice.model.Item
import com.example.mypractice.view.adapter.UserListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/** View models manipulates models and read data from models */
class UserListViewModel : BaseViewModel() {
    /**Inject Remote interface*/
    @Inject
    lateinit var postApi: RemoteApi

    /** initialize later
     * and dispose after use */
    private lateinit var subscription: Disposable

    /** UserList adapter views the user data,
     *  this adapter set in "activity_main.xml"
     *  by using the BindingAdapter */
    val userListAdapter: UserListAdapter =
        UserListAdapter()

    /** Mutable variables */
    private var userFilterList: MutableList<Item> = mutableListOf()
    private var oldFilteredList: MutableList<Item> = mutableListOf()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    /** Hold the searching username */
    var searchUserName: String? = null

    init {
        /**Initial data load */
        loadPosts()
    }

    private fun loadPosts() {
        subscription = postApi.getData(
            /** Query @param */
            ApiConstants.ORDER,
            ApiConstants.SORT,
            ApiConstants.SITE
        )   /**
        The subscriber observes in the main thread
        Observable is called outside the main thread
         */
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveUserListStart() }    /** this methods updates the ui*/
            .doOnTerminate { onRetrieveUserListFinish() }
            .subscribe(
                /** Add result */
                { result -> onRetrieveUserListSuccess(result.items) },
                { onRetrieveUserListError() }
            )

    }

    /** When start loading */
    private fun onRetrieveUserListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    /** loading finish */
    private fun onRetrieveUserListFinish() {
        loadingVisibility.value = View.GONE
    }

    /** Get data successfully */
    private fun onRetrieveUserListSuccess(userList: List<Item>) {
        userListAdapter.updateUserList(userList)
        /**Add userList data into userFilterList for further use of searching */
        userList!!.let { userFilterList.addAll(it) }
        /**Add userList data into oldFilteredList for save original data */
        userList!!.let { oldFilteredList.addAll(it) }
    }

    /**Error while loading data */
    private fun onRetrieveUserListError() {
        errorMessage.value = R.string.post_error
    }

    /** Filter recycler view when user name submitted */
    fun filerUserList(view: View) {
        if (searchUserName!!.isNotEmpty()) {
            userListAdapter.filter.filter(searchUserName)
        }
    }

    /** Dispose call from UserListViewModel when this ViewModel is cleared to avoid the memory leak*/
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}