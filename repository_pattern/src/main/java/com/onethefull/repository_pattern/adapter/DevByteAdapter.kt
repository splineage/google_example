package com.onethefull.repository_pattern.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onethefull.repository_pattern.R
import com.onethefull.repository_pattern.databinding.ItemDevbyteBinding
import com.onethefull.repository_pattern.domain.DevByteVideo

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/06/28 3:14 오후
 * @desc
 */
class DevByteAdapter(val callback: VideoClick): RecyclerView.Adapter<DevByteViewHolder>() {
    var videos: List<DevByteVideo> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val withDataBinding: ItemDevbyteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DevByteViewHolder.LAYOUT,
            parent,
            false
        )
        return DevByteViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {
        holder.binding.also {
            it.video = videos[position]
            it.videoCallback = callback
        }
    }

    override fun getItemCount(): Int = videos.size

}

class DevByteViewHolder(val binding: ItemDevbyteBinding): RecyclerView.ViewHolder(binding.root){
    companion object{
        @LayoutRes
        val LAYOUT = R.layout.item_devbyte
    }
}

class VideoClick(val block: (DevByteVideo)-> Unit){
    fun onClick(video: DevByteVideo) = block(video)
}
