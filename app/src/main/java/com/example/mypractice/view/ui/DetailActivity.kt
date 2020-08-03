package com.example.mypractice.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mypractice.R
import com.example.mypractice.databinding.ActivityDetailBinding
import com.example.mypractice.model.Item
import com.example.mypractice.viewmodel.AdapterViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: AdapterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_detail
        )
        supportActionBar
        viewModel = ViewModelProviders.of(this).get(AdapterViewModel::class.java)
        binding.viewModel = viewModel
        val userDetail = intent.getParcelableExtra<Item>("userDetail")
        if (userDetail != null) {
            supportActionBar!!.title = userDetail.displayName
            viewModel.bind(userDetail)

        }


    }
}