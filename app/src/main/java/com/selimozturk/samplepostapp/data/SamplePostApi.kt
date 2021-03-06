package com.selimozturk.samplepostapp.data

import com.selimozturk.samplepostapp.data.remote.entities.Comment
import com.selimozturk.samplepostapp.data.remote.entities.Post
import com.selimozturk.samplepostapp.data.remote.entities.user.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SamplePostApi {

    @GET("posts")
    suspend fun getPosts(@Query("_page") page: Int): List<Post>

    @GET("users/{id}")
    suspend fun getUserInfo(@Path("id") id: Int): User

    @GET("comments")
    suspend fun getPostComments(
        @Query("postId") postId: String = "",
    ): List<Comment>

}