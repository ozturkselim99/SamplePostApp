package com.selimozturk.samplepostapp.data

import com.selimozturk.samplepostapp.data.remote.entities.Comment
import com.selimozturk.samplepostapp.data.remote.entities.Post
import com.selimozturk.samplepostapp.data.remote.entities.user.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SamplePostApi {
    @GET("posts")
    suspend fun getPosts() : Response<List<Post>>

    @GET("users/{id}")
    suspend fun getUserInfo(@Path("id") id: Int): Response<User>

    @GET("comments")
    suspend fun getPostComments(
        @Query("postId") postId: String="",
    ): Response<List<Comment>>
}