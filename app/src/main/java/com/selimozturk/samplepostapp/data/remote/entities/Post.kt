package com.selimozturk.samplepostapp.data.remote.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.map
import com.selimozturk.samplepostapp.domain.model.PostDomain

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

fun LiveData<PagingData<Post>>.postListToPostDomainDataList(): LiveData<PagingData<PostDomain>> {
    return map { pagingData ->
        pagingData.map { post ->
            PostDomain(
                id = post.id,
                userId = post.userId,
                title = post.title,
                body = post.body,
            )
        }
    }
}