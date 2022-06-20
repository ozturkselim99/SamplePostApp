package com.selimozturk.samplepostapp.data.remote.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.map
import com.selimozturk.samplepostapp.domain.model.PostDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

fun LiveData<PagingData<Post>>.postListToPostDomainDataList(): LiveData<PagingData<PostDomain>> {
    return map {pagingData->
        pagingData.map {
            PostDomain(
                id = it.id,
                userId=it.userId,
                title = it.title,
                body = it.body,
            )
        }
    }
}