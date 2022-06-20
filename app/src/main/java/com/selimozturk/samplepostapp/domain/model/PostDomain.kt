package com.selimozturk.samplepostapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDomain(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) : Parcelable

