package com.prabin.saveoassignment.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prabin.saveoassignment.clicklisteners.ShowClickListener
import com.prabin.saveoassignment.databinding.ActivityMainBinding
import com.prabin.saveoassignment.model.ResponseModel
import com.prabin.saveoassignment.model.ShowModel
import com.prabin.saveoassignment.repository.MoviesRepo
import com.prabin.saveoassignment.viewmodel.MoviesViewModel
import com.prabin.saveoassignment.viewmodel.MoviesViewModelFactory
import com.prabin.saveoassignment.views.recyclerview.HorShowsAdapter
import com.prabin.saveoassignment.views.recyclerview.ShowsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ShowClickListener {

    private lateinit var binding: ActivityMainBinding
    private val responseList = mutableListOf<ResponseModel>()
    private val showsModel = mutableListOf<ShowModel>()
    lateinit var showsAdapter: ShowsAdapter
    lateinit var horShowsAdapter: HorShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repo = MoviesRepo()
        val viewModelFactory = MoviesViewModelFactory(repo)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)

        binding.rvHorizontalShows.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        horShowsAdapter = HorShowsAdapter(responseList, this)
        rvHorizontalShows.adapter = horShowsAdapter

        binding.rvShows.layoutManager = GridLayoutManager(this, 3)
        showsAdapter = ShowsAdapter(showsModel, this)
        rvShows.adapter = showsAdapter

        callApi(viewModel)

    }

    private fun callApi(viewModel: MoviesViewModel) {
        viewModel.getShows(1).observe(this, {
            showsModel.clear()
            val result = it.data!!
            showsModel.addAll(result)
            showsAdapter.notifyDataSetChanged()
            Log.d("prabin", showsModel.size.toString())
        })

        viewModel.getMovies("god").observe(this, {
            responseList.clear()
            val result = it.data!!
            responseList.addAll(result)
            horShowsAdapter.notifyDataSetChanged()
            Log.d("prabin", responseList.size.toString())
        })
    }

    override fun onShowClicked(showModel: ShowModel) {

    }

    override fun onMovieClicked(responseModel: ResponseModel) {

    }
}