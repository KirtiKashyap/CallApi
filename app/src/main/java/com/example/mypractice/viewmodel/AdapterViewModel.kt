package com.example.mypractice.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.mypractice.base.BaseViewModel
import com.example.mypractice.model.BadgeCounts
import com.example.mypractice.model.Item
import com.example.mypractice.view.ui.DetailActivity


class AdapterViewModel : BaseViewModel() {
    /** Hold Mutable data */
    private val userName = MutableLiveData<String>()
    private val reputation = MutableLiveData<String>()
    private val location = MutableLiveData<String>()
    private val badgeCount = MutableLiveData<BadgeCounts>()
    private val badges = MutableLiveData<String>()
    private val creationData = MutableLiveData<String>()
    private val item = MutableLiveData<Item>()

    /** Initialize later Hold image url */
    lateinit var imageUrl: String

    /** Bind Mutable data to mutable variables */
    fun bind(userItem: Item) {
        item.value = userItem
        userName.value = userItem.displayName
        reputation.value = userItem.reputation.toString()
        location.value = userItem.location
        creationData.value = userItem.creationDate.toString()
        badgeCount.value = userItem.badgeCounts
        imageUrl = userItem.profileImage
    }

    /** Get User Name */
    fun getUserName(): MutableLiveData<String> {
        return userName
    }

    /** Get Reputation*/
    fun getReputation(): MutableLiveData<String> {
        return reputation
    }

    /** Get Location */
    fun getLocation(): MutableLiveData<String> {
        return location
    }

    /** Grt Creation data */
    fun getCreationDate(): MutableLiveData<String> {
        return creationData
    }

    /** Get Badges */
    fun getBadges(): MutableLiveData<String> {
        badges.value =
            "Gold: " + badgeCount.value!!.gold + "\n" + "Silver: " + badgeCount.value!!.silver + "\n" + "Bronze: " + badgeCount.value!!.bronze
        return badges
    }

    /** Click for Detail Activity */
    fun onClick(view: View) {
        Intent(view.context, DetailActivity::class.java).also {
            it.putExtra("userDetail", item.value)
            view.context.startActivity(it)
        }
    }

}