package com.prabin.nobrokerassignment.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("shivarajp/2cbe00030c04472c9d8ad4b0ec112dbe/raw/c651391e428182f08d60d59e79073f3fcf79b858/nobroker")
    fun getData(): Call<List<ResponseDTO>>
}