package com.onethefull.repository_pattern.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/06/28 12:51 오전
 * @desc
 */

/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("isNetworkError", "playlist")
fun hideIfNetworkError(view: View, isNetworkError: Boolean, playlist: Any?){
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE

    if (isNetworkError){
        view.visibility = View.GONE
    }
}


/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String){
    Glide.with(imageView.context).load(url).into(imageView)
}