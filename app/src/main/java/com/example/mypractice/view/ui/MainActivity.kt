package com.example.mypractice.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypractice.R
import com.example.mypractice.viewmodel.UserListViewModel
import com.example.mypractice.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable


class MainActivity : AppCompatActivity() {
    private val disposable = CompositeDisposable()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserListViewModel
    private var errorSnackbar: Snackbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.userList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        binding.viewModel = viewModel


        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

    }
    /** Call on error while loading data */
    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        /** Using clear will clear all, but can accept new disposable */
        disposable.clear()
    }
}