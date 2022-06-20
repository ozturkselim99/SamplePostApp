package com.selimozturk.samplepostapp.domain.model

data class CommentDomain(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
