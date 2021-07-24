package com.prabin.nobrokerassignment.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("b/60fa8fefa917050205ce5470")
    fun getData(): Call<List<ResponseDTO>>
}