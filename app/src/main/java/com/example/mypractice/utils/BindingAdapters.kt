package com.example.mypractice.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
This annotation is telling the Data Binding library this  is a custom setter named “adapter”.
We don’t need to declare this in any config files or anything –the annotation itself is enough to ensure that this is recognised at build time,
and we can now invoke this from an expression:
 */
/** Set Recyclerview adapter in "activity_main.xml"*/
@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

/** Set Progressbar visibility for Apicall in "UserListViewModel" */
@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

/** Set text of TextView in  "user_item.xml" */
@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

/** Set ulr of ImageView in "user_item.xml"*/
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val context: Context = imageView.context
    Glide.with(context).load(url).into(imageView)
}


