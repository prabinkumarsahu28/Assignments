package com.prabin.saveoassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.prabin.saveoassignment.model.MoviesModel
import com.prabin.saveoassignment.network.Resource
import com.prabin.saveoassignment.repository.MoviesRepo
import kotlinx.coroutines.Dispatchers

class MoviesViewModel(private val repo: MoviesRepo) : ViewModel() {
    fun getShows(search: Int): LiveData<Resource<List<MoviesModel>>> {

        return liveData(Dispatchers.IO) {
            val result = repo.getShows(search)
            emit(result)
        }
    }
}