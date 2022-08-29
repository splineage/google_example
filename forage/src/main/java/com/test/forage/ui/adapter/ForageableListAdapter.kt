package com.test.forage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.forage.databinding.ListItemForageableBinding
import com.test.forage.model.Forageable

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 3:52 오후
 * @desc
 */
class ForageableListAdapter(private val clickListener: (Forageable)->Unit): ListAdapter<Forageable, ForageableListAdapter.ForageableViewHolder>(DiffCallback){
    class ForageableViewHolder(private var binding: ListItemForageableBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(forageable: Forageable){
            binding.forageable = forageable
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForageableViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ForageableViewHolder(ListItemForageableBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ForageableViewHolder, position: Int) {
        val forageable = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(forageable)
        }
        holder.bind(forageable)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Forageable>(){
        override fun areItemsTheSame(oldItem: Forageable, newItem: Forageable): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Forageable, newItem: Forageable): Boolean {
            return oldItem == newItem
        }
    }
}