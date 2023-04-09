package com.movingroot.flowassignment.presentation.ui.recently_browsed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.movingroot.flowassignment.data.model.BrowsedRecordEntity
import com.movingroot.flowassignment.databinding.AdapterBrowsedBinding
import com.movingroot.flowassignment.presentation.utils.ViewUtil.onSingleClick

class BrowsedAdapter(private val delegate: RecentlyBrowsedDelegate) :
    ListAdapter<BrowsedRecordEntity, BrowsedAdapter.ViewHolder>(
        DiffUtilItemCallback
    ) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterBrowsedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemId(position: Int): Long = getItem(position).id

    inner class ViewHolder(private val binding: AdapterBrowsedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            getItem(position).run {
                binding.data = this
                setDelegate(this)
            }
        }

        private fun setDelegate(record: BrowsedRecordEntity) {
            binding.keyword.onSingleClick {
                delegate.browse(record.keyword)
            }
            binding.btnDelete.onSingleClick {
                delegate.delete(record)
            }
        }
    }
}

private object DiffUtilItemCallback : DiffUtil.ItemCallback<BrowsedRecordEntity>() {
    override fun areItemsTheSame(oldItem: BrowsedRecordEntity, newItem: BrowsedRecordEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BrowsedRecordEntity, newItem: BrowsedRecordEntity): Boolean {
        return oldItem == newItem
    }
}
