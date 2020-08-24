package com.example.mypractice.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypractice.R
import com.example.mypractice.databinding.ActivityMainBinding
import com.example.mypractice.view.adapter.UserListAdapter
import com.example.mypractice.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserListViewModel
    private val adapter = UserListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.userList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        viewModel.userPagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        user_list.adapter = adapter
        binding.viewModel = viewModel

    }

}