package com.medieverone.feature_timetracker.feature_timetracker_impl.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medieverone.core_utils.extensions.toFormattedTimeString
import com.medieverone.feature_timetracker.databinding.TimelogItemBinding
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity

class TimeLogAdapter: RecyclerView.Adapter<TimeLogViewHolder>() {

    private val items: ArrayList<TimeLogEntity> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLogViewHolder {
        val itemBinding = TimelogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeLogViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TimeLogViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    fun initItems(newItems: List<TimeLogEntity>) {
        items.clear()
        items.addAll(newItems)
        this.notifyDataSetChanged()
    }

    fun addItems(newItems: List<TimeLogEntity>) {
        items.addAll(newItems)
        this.notifyDataSetChanged()
    }
}

class TimeLogViewHolder(private val binding: TimelogItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TimeLogEntity) {
        binding.tvActivity.text = item.activity?.tag ?: ""
        binding.tvDate.text = item.date.toString()
        binding.tvTime.text = item.time.toFormattedTimeString
    }
}