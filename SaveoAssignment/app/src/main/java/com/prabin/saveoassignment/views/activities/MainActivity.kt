package com.prabin.saveoassignment.views.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
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
    private lateinit var showsAdapter: ShowsAdapter
    private lateinit var horShowsAdapter: HorShowsAdapter
    var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.shimmerHorizontal.startShimmer()
        binding.shimmerVertical.startShimmer()

        val repo = MoviesRepo()
        val viewModelFactory = MoviesViewModelFactory(repo)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)

        val layoutManager = GridLayoutManager(this, 3)

        verticalRecyclerView(layoutManager)
        horizontalRecyclerView()
        callApi(viewModel)
        pagination(layoutManager, viewModel, this)

    }

    /**
     * this function is used to implement unlimited scrolling
     */
    private fun pagination(
        layoutManager: GridLayoutManager,
        viewModel: MoviesViewModel,
        mainActivity: MainActivity
    ) {
        var loading = true
        var pastVisibleItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        binding.rvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            count++
                            loading = false
                            Log.v("prabin", "$count")
                            viewModel.getShows(count).observe(mainActivity, {
                                val result = it.data!!
                                showsModel.addAll(result)
                                showsAdapter.notifyDataSetChanged()
                                Log.d("prabin", showsModel.size.toString())
                            })
                            loading = true
                        }
                    }
                }
            }
        })
    }

    /**
     * This function is setting data in top horizontal recyclerView
     */
    private fun horizontalRecyclerView() {
        binding.rvHorizontalShows.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        horShowsAdapter = HorShowsAdapter(responseList, this)
        rvHorizontalShows.adapter = horShowsAdapter
    }

    /**
     * This function is setting all shows data in recyclerView
     */
    private fun verticalRecyclerView(layoutManager: GridLayoutManager) {
        binding.rvShows.layoutManager = layoutManager
        showsAdapter = ShowsAdapter(showsModel, this)
        rvShows.adapter = showsAdapter
    }

    /**
     * This function is used to get api data
     */
    private fun callApi(viewModel: MoviesViewModel) {
        viewModel.getShows(1).observe(this, {
            binding.apply {
                shimmerVertical.hideShimmer()
                shimmerVertical.visibility = View.GONE
                tvNowShowing.visibility = View.VISIBLE
            }
            showsModel.clear()
            val result = it.data!!
            showsModel.addAll(result)
            showsAdapter.notifyDataSetChanged()
            Log.d("prabin", showsModel.size.toString())
        })

        viewModel.getMovies("god").observe(this, {
            binding.shimmerHorizontal.hideShimmer()
            binding.shimmerHorizontal.visibility = View.GONE
            responseList.clear()
            val result = it.data!!
            responseList.addAll(result)
            horShowsAdapter.notifyDataSetChanged()
            Log.d("prabin", responseList.size.toString())
        })
    }

    /**
     * This function listens the click of all shows from recyclerView and sends data of that item
     * to the next activity to show details
     */
    override fun onShowClicked(showModel: ShowModel, ivImgShow: ImageView) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("showModel", showModel)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            ivImgShow,
            ViewCompat.getTransitionName(ivImgShow)!!
        )
        startActivity(intent, options.toBundle())
    }

    /**
     * This function listens the click of top horizontal recyclerView and sends data of that item
     * to the next activity to show details
     */
    override fun onMovieClicked(responseModel: ResponseModel, ivHorImg: ImageView) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("showModel", responseModel.show)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            ivHorImg,
            ViewCompat.getTransitionName(ivHorImg)!!
        )
        startActivity(intent, options.toBundle())
    }
}