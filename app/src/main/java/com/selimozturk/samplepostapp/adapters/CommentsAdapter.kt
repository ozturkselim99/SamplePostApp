package com.selimozturk.samplepostapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selimozturk.samplepostapp.databinding.CommentItemRowBinding
import com.selimozturk.samplepostapp.domain.model.CommentDomain

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    var items: List<CommentDomain> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CommentsViewHolder(private val binding: CommentItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(commentDomain: CommentDomain) {
            binding.comment = commentDomain
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val binding =
            CommentItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}