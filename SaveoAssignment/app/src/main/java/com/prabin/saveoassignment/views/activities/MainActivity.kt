package com.prabin.saveoassignment.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.prabin.saveoassignment.databinding.ActivityMainBinding
import com.prabin.saveoassignment.model.MoviesModel
import com.prabin.saveoassignment.network.ApiClient
import com.prabin.saveoassignment.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        callApi()

    }

    private fun callApi() {
        val apiClient = Network.getInstance().create(ApiClient::class.java)
        apiClient.getData("all").enqueue(object : Callback<List<MoviesModel>> {
            override fun onResponse(
                call: Call<List<MoviesModel>>,
                response: Response<List<MoviesModel>>
            ) {

                val itemList = response.body()
                Log.d("prabin", itemList!!.size.toString())
            }

            override fun onFailure(call: Call<List<MoviesModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No Connection", Toast.LENGTH_SHORT).show()
            }
        })
    }
}