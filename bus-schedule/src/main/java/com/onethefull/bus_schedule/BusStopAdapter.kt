package com.onethefull.bus_schedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onethefull.bus_schedule.database.Schedule
import com.onethefull.bus_schedule.databinding.BusStopItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class BusStopAdapter(private val onItemClicked: (Schedule) -> Unit)
    : ListAdapter<Schedule, BusStopAdapter.BusStopViewHolder>(DiffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val viewHodler = BusStopViewHolder(
            BusStopItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHodler.itemView.setOnClickListener {
            val position = viewHodler.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHodler
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BusStopViewHolder(private var binding: BusStopItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule){
            binding.stopNameTextView.text = schedule.stopName
            binding.arrivalTimeTextView.text = SimpleDateFormat(
                    "h:mm a"
                ).format(Date(schedule.arrivalTime.toLong() * 1000))
        }
    }

    companion object{
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>(){
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }
}