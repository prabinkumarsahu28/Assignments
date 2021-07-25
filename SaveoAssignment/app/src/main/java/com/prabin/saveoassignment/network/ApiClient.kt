package com.prabin.saveoassignment.network

import com.prabin.saveoassignment.model.ResponseModel
import com.prabin.saveoassignment.model.ShowModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("shows")
    suspend fun getData(@Query("page") search: Int): List<ShowModel>

    @GET("search/shows")
    suspend fun getMovies(@Query("q") s: String): List<ResponseModel>
}