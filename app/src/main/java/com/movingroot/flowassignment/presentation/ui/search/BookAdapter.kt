package com.movingroot.flowassignment.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movingroot.flowassignment.R
import com.movingroot.flowassignment.data.model.Book
import com.movingroot.flowassignment.databinding.AdapterBookBinding
import com.movingroot.flowassignment.presentation.utils.Constants
import com.movingroot.flowassignment.presentation.utils.ViewUtil.onSingleClick
import kotlin.math.min

class BookAdapter(private val delegate: SearchDelegate) :
    ListAdapter<Book, BookAdapter.ViewHolder>(DiffUtilItemCallback) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterBookBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemId(position: Int): Long = getItem(position).id

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        Glide.with(recyclerView).pauseRequests()
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.findViewById<ImageView>(R.id.thumbnail)
            .run {
                Glide.with(holder.itemView.context).clear(this)
            }
    }

    inner class ViewHolder(private val binding: AdapterBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            getItem(position).run {
                binding.data = this
                loadThumbnail()
                setDelegate(link)
                preload(position)
            }
        }

        private fun Book.loadThumbnail() {
            val sizes = getThumbnailSize()
            Glide.with(itemView.context)
                .load(image)
                .override(sizes[0], sizes[1])
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(binding.thumbnail)
        }

        private fun preload(position: Int) {
            try {
                val exclEnd = min(position + Constants.THUMBNAIL_PRELOAD_MAX_CNT, currentList.size)
                currentList.subList(position, exclEnd)
                    .map { it }
                    .forEach { book ->
                        preloadThumbnail(book)
                    }
            } catch (_: IndexOutOfBoundsException) {
            }
        }

        private fun preloadThumbnail(book: Book) {
            val sizes = getThumbnailSize()
            Glide.with(itemView.context)
                .load(book.image)
                .preload(sizes[0], sizes[1])
        }

        private fun getThumbnailSize(): IntArray {
            return intArrayOf(
                itemView.context.resources.getDimension(R.dimen.book_thumbnail_w).toInt(),
                itemView.context.resources.getDimension(R.dimen.book_thumbnail_h).toInt()
            )
        }

        private fun setDelegate(link: String) {
            binding.background.onSingleClick {
                delegate.navWithLink(link)
            }
        }
    }
}

private object DiffUtilItemCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}
