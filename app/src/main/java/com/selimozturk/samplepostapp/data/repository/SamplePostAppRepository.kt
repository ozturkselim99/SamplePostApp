package com.selimozturk.samplepostapp.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.selimozturk.samplepostapp.data.SamplePostApi
import com.selimozturk.samplepostapp.data.remote.entities.Post
import com.selimozturk.samplepostapp.data.remote.entities.commentDataToCommentDomainData
import com.selimozturk.samplepostapp.data.remote.entities.user.userDataToUserDomainData
import com.selimozturk.samplepostapp.domain.model.CommentDomain
import com.selimozturk.samplepostapp.domain.model.UserDomain
import com.selimozturk.samplepostapp.data.pagination.PostPagingDataSource
import javax.inject.Inject

class SamplePostAppRepository @Inject constructor(
    private val api: SamplePostApi,
    private val sharedPreferences: SharedPreferences,
) {

    fun getPosts(): LiveData<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { PostPagingDataSource(api) }
        ).liveData
    }

    suspend fun getUserInfo(id: Int): UserDomain {
        return api.getUserInfo(id).userDataToUserDomainData()
    }

    suspend fun getPostComments(postId: String): List<CommentDomain> {
        return api.getPostComments(postId).commentDataToCommentDomainData()
    }

    fun signUp(email: String, password: String) {
        sharedPreferences.edit().putString("email", email).apply()
        sharedPreferences.edit().putString("password", password).apply()
    }

    fun isUserSignedUp(email: String, password: String): Boolean {
        return sharedPreferences.getString(
            "email",
            ""
        ) == email && sharedPreferences.getString("password", "") == password
    }

}