package com.selimozturk.samplepostapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.selimozturk.samplepostapp.data.SamplePostApi
import com.selimozturk.samplepostapp.data.remote.entities.Post
import com.selimozturk.samplepostapp.pagination.PostPagingDataSource
import javax.inject.Inject

class SamplePostAppRepository @Inject constructor(
    private val api: SamplePostApi
) {
    fun getPosts(): LiveData<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { PostPagingDataSource(api) }
        ).liveData
    }
}