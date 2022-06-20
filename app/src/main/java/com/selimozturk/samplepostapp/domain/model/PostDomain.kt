package com.selimozturk.samplepostapp.domain.model


data class PostDomain(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

