package com.rk.mvvm.DiffUtils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rk.mvvm.R

class ProgramingAdapter:
    ListAdapter<ProgramingItem, ProgramingAdapter.ProgramingViewHolder>(DiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ProgramingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgramingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ProgramingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.textView7)
        val initial = view.findViewById<TextView>(R.id.textView8)

        fun bind(item: ProgramingItem) {
            name.text = item.name
            initial.text = item.initial
        }
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ProgramingItem>()
    {
        override fun areItemsTheSame(oldItem: ProgramingItem, newItem: ProgramingItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProgramingItem, newItem: ProgramingItem): Boolean {
            return  oldItem == newItem
        }
    }
}