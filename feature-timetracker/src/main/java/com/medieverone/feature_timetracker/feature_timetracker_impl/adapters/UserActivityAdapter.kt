package com.medieverone.feature_timetracker.feature_timetracker_impl.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import com.medieverone.feature_timetracker.R
import com.medieverone.feature_timetracker.databinding.UserActivityItemBinding

class UserActivityAdapter(
    private val context: Context,
    private val callback: Callback? = null
) : BaseAdapter(), SpinnerAdapter {

    interface Callback {

        fun onItemClicked(activity: ActivityEntity)
    }

    val items: ArrayList<ActivityEntity> = arrayListOf()
    private var _binding: UserActivityItemBinding? = null
    private val binding get() = _binding!!

    override fun getCount() = items.size

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = View.inflate(context, R.layout.user_activity_item, null)
        _binding = UserActivityItemBinding.bind(view)

        binding.tvUserActivityText.text = items[position].name
        view.setOnClickListener {
            callback?.onItemClicked(items[position])
        }
        return view
    }

}