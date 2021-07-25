package com.prabin.saveoassignment.network

import com.prabin.saveoassignment.model.MoviesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("search/shows")
    fun getData(@Query("q") search: String?): Call<List<MoviesModel>>
}