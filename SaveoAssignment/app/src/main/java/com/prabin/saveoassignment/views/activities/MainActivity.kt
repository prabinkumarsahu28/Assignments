package com.prabin.saveoassignment.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.prabin.saveoassignment.databinding.ActivityMainBinding
import com.prabin.saveoassignment.model.MoviesModel
import com.prabin.saveoassignment.repository.MoviesRepo
import com.prabin.saveoassignment.viewmodel.MoviesViewModel
import com.prabin.saveoassignment.viewmodel.MoviesViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val list = mutableListOf<MoviesModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repo = MoviesRepo()
        val viewModelFactory = MoviesViewModelFactory(repo)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)

        callApi(viewModel)

    }

    private fun callApi(viewModel: MoviesViewModel) {
        viewModel.getShows(1).observe(this, {
            list.clear()
            val result = it.data!!
            list.addAll(result)
            Log.d("prabin", list.size.toString())
        })
    }
}