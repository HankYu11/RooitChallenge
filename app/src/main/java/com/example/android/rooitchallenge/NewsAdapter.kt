package com.example.android.rooitchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.rooitchallenge.data.domain.News
import com.example.android.rooitchallenge.databinding.ListItemNewsBinding

class NewsAdapter: ListAdapter<News, NewsAdapter.ViewHolder>(NewsDiffCallback()) {

    class ViewHolder private constructor(private val binding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: News) {
            with(binding) {
                newsTitle.text = item.titleSource

                newsImage.load(item.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_baseline_article_24)
                    fallback(R.drawable.ic_baseline_article_24)
                    error(R.drawable.ic_baseline_error_24)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return ViewHolder(ListItemNewsBinding.inflate(inflater, parent, false))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

private class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        // We don't have a id to distinguish items, compare the content for now
        return areContentsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}