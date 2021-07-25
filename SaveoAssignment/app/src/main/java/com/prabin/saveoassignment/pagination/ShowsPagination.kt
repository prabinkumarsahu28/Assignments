package com.prabin.saveoassignment.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prabin.saveoassignment.model.ShowModel
import com.prabin.saveoassignment.network.ApiClient

class ShowsPagination(
    private val apiClient: ApiClient
) : PagingSource<Int, ShowModel>() {
    private val START_PAGE = 1
    override fun getRefreshKey(state: PagingState<Int, ShowModel>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowModel> {
        val pos = params.key ?: START_PAGE
        Log.d("prabin", "paging")
        return try {
            val response = apiClient.getData(pos)
            LoadResult.Page(
                data = response,
                prevKey = if (pos == START_PAGE) null else pos - 1,
                nextKey = if (response.isEmpty()) null else pos + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}