package com.selimozturk.samplepostapp.data.remote.entities

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)