package com.prabin.saveoassignment.repository

import com.prabin.saveoassignment.model.MoviesModel
import com.prabin.saveoassignment.network.ApiClient
import com.prabin.saveoassignment.network.Network
import com.prabin.saveoassignment.network.Resource
import com.prabin.saveoassignment.network.ResponseHandler


class MoviesRepo {
    private val apiClient = Network.getInstance().create(ApiClient::class.java)
    private val responseHandler = ResponseHandler()

    suspend fun getShows(search: Int): Resource<List<MoviesModel>> {
        val result = apiClient.getData(search)
        return try {
            responseHandler.handleSuccess(result)

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}