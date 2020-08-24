package com.example.mypractice.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypractice.R
import com.example.mypractice.databinding.UserItemBinding
import com.example.mypractice.model.Item
import com.example.mypractice.viewmodel.AdapterViewModel

/** Create the basic adapter extending from RecyclerView.Adapter
Note that we specify the custom ViewHolder which gives us access to our views*/
class UserListAdapter : PagedListAdapter<Item, UserListAdapter.ViewHolder>(USER_COMPARATOR) {

    /** Create new views (invoked by the layout manager) */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: UserItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_item, parent, false
        )
        return ViewHolder(
            binding
        )
    }

/** Replace the contents of a view (invoked by the layout manager) */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    /** Bind data to the ViewHolder class*/
    val user = getItem(position)
    user?.let { holder.bind(user) }
}

    /** Provide a reference to the views for each data item */
    class ViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        /** ViewModel for adapter*/
        private val viewModel = AdapterViewModel()
        fun bind(
            userItem: Item
        ) {
            /** bind data to the ViewModel*/
            viewModel.bind(userItem)
            binding.viewModel = viewModel
        }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.userId == newItem.userId

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                newItem == oldItem

        }
    }
}