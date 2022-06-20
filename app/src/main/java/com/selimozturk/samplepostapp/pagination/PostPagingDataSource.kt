package com.selimozturk.samplepostapp.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.selimozturk.samplepostapp.data.SamplePostApi
import com.selimozturk.samplepostapp.data.remote.entities.Post
import javax.inject.Inject

class PostPagingDataSource @Inject constructor(
    private val api: SamplePostApi,
) :
    PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getPosts()
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return null
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}