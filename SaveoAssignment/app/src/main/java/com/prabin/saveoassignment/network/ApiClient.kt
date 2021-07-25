package com.prabin.saveoassignment.network

import com.prabin.saveoassignment.model.MoviesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("shows")
    suspend fun getData(@Query("page") search: Int): List<MoviesModel>
}