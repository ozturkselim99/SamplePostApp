package com.selimozturk.samplepostapp.data.remote.entities

import com.selimozturk.samplepostapp.domain.model.CommentDomain

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
fun List<Comment>.commentDataToCommentDomainData(): List<CommentDomain> {
    return map {
        CommentDomain(
            it.postId,it.id,it.name,it.email,it.body
        )
    }
}