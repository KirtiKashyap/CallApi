package com.example.mypractice.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypractice.R
import com.example.mypractice.databinding.UserItemBinding
import com.example.mypractice.model.Item
import com.example.mypractice.viewmodel.AdapterViewModel

/** Create the basic adapter extending from RecyclerView.Adapter
Note that we specify the custom ViewHolder which gives us access to our views*/
class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>(), Filterable {

    /** Store a member variable for the users */
    private lateinit var userList: List<Item>

    /** Store a member variable for the search filtered users */
    private lateinit var userListFiltered: List<Item>

    /** Create the RecyclerView row instance */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: UserItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_item, parent, false
        )
        return ViewHolder(
            binding
        )
    }

    /** set the size of list*/
    override fun getItemCount(): Int {
        return if (::userListFiltered.isInitialized) userListFiltered.size else 0
    }

    /** Bind the data to the RecyclerView row */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /** Bind data to the ViewHolder class*/
        holder.bind(userListFiltered[position])
    }

    /** update users data and view */
    fun updateUserList(userList: List<Item>) {
        this.userList = userList
        userListFiltered = userList
        notifyDataSetChanged()
    }

    /** custom ViewHolder for accessing to views */
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

    /** RecyclerView filter by implementing the "Filterable" */
    override fun getFilter(): Filter {
        return object : Filter() {
            /** Search char for filter */
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString: String = constraint.toString()
                if (charString.isEmpty()) {
                    userListFiltered = userList
                } else {
                    val filteredList: MutableList<Item> = ArrayList()
                    for (row in userList) {

                        /**
                        name match condition. this might differ depending on our requirement
                        here we are looking for name match
                         */

                        if (row.displayName.toLowerCase()
                                .contains(charString.toLowerCase()) || constraint?.let {
                                row.displayName
                                    .contains(it)
                            }!!
                        ) {
                            filteredList.add(row)
                        }
                    }
                    userListFiltered = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = userListFiltered
                return filterResults

            }

            /** update filter result and update view */
            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
                userListFiltered = filterResults?.values as List<Item>
                notifyDataSetChanged()
            }
        }
    }

}