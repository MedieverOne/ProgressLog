package com.medieverone.feature_timetracker.feature_timetracker_impl.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.medieverone.core_utils.extensions.toFormattedTimeString
import com.medieverone.feature_timetracker.databinding.UserActivityLogItemBinding
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity


class UserActivitiesLogAdapter(
    private val callback: Callback
) : RecyclerView.Adapter<UserActivityLogViewHolder>() {

    interface Callback {

        fun onItemRightSlide(item: UserActivityEntity)
    }

    private val items: ArrayList<UserActivityEntity> = arrayListOf()
    var itemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            when(swipeDir) {
                ItemTouchHelper.RIGHT -> {
                    callback.onItemRightSlide(item = items[position])
                }
                else -> {}
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserActivityLogViewHolder {
        val itemBinding = UserActivityLogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserActivityLogViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserActivityLogViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    fun initItems(newItems: List<UserActivityEntity>) {
        items.clear()
        items.addAll(newItems)
        this.notifyDataSetChanged()
    }

    fun addItems(newItems: List<UserActivityEntity>) {
        items.addAll(newItems)
        this.notifyDataSetChanged()
    }
}

class UserActivityLogViewHolder(private val binding: UserActivityLogItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UserActivityEntity) {
        binding.tvActivityTag.text = item.tag
        binding.tvActivityTotalTime.text = item.summaryTimeInMillis.toFormattedTimeString
    }
}
