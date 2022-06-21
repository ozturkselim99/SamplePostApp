package com.selimozturk.samplepostapp.data.remote.entities.user

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo,
)