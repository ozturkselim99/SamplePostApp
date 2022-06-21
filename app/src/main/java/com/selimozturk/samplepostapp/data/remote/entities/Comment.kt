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
    return map { comment ->
        CommentDomain(
            comment.postId, comment.id, comment.name, comment.email, comment.body
        )
    }
}