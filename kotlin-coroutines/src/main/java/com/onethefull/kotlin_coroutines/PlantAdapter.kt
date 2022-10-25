package com.onethefull.kotlin_coroutines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onethefull.kotlin_coroutines.databinding.ListItemPlantBinding

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/24 1:56 오후
 * @desc
 */
class PlantAdapter: ListAdapter<Plant, RecyclerView.ViewHolder>(PlantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlantViewHolder(
            ListItemPlantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PlantViewHolder).bind(plant)
    }

    inner class PlantViewHolder(
        private val binding: ListItemPlantBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Plant){
            binding.apply {
                plant = item
                executePendingBindings()
            }
        }
    }
}

private class PlantDiffCallback: DiffUtil.ItemCallback<Plant>(){
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}