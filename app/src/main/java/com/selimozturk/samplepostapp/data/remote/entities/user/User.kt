package com.selimozturk.samplepostapp.data.remote.entities.user

import com.selimozturk.samplepostapp.domain.model.UserDomain

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)

fun User.userDataToUserDomainData(): UserDomain {
    return UserDomain(
        id,name,username,email
    )
}