package com.prabin.saveoassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prabin.saveoassignment.repository.MoviesRepo

class MoviesViewModelFactory(private val repo: MoviesRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(repo) as T
    }
}