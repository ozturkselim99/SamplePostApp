package com.selimozturk.samplepostapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.selimozturk.samplepostapp.databinding.PostItemRowBinding
import com.selimozturk.samplepostapp.domain.model.PostDomain

class PostsAdapter(var onItemClicked: ((PostDomain) -> Unit) = {}) :
    PagingDataAdapter<PostDomain, PostsAdapter.PostsViewHolder>(DiffUtilCallBack()) {

    inner class PostsViewHolder(private val binding: PostItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postDomain: PostDomain) {
            binding.detailButton.setOnClickListener {
                onItemClicked.invoke(postDomain)
            }
            binding.post = postDomain
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<PostDomain>() {
        override fun areItemsTheSame(
            oldItem: PostDomain,
            newItem: PostDomain
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PostDomain,
            newItem: PostDomain
        ): Boolean {
            return oldItem == newItem
        }
    }

}