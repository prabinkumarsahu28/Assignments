package com.prabin.nobrokerassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.prabin.nobrokerassignment.R
import com.prabin.nobrokerassignment.data.ApiClient
import com.prabin.nobrokerassignment.data.Network
import com.prabin.nobrokerassignment.data.ResponseDTO
import com.prabin.nobrokerassignment.listeners.ItemClickListener
import com.prabin.nobrokerassignment.recyclerview.DataAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ItemClickListener {
    private var itemList = listOf<ResponseDTO>()
    private lateinit var dataAdapter: DataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        callApi(this)
    }

    private fun callApi(itemClickListener: ItemClickListener) {
        val apiClient = Network.getInstance().create(ApiClient::class.java)

        apiClient.getData().enqueue(object : Callback<List<ResponseDTO>> {
            override fun onResponse(
                call: Call<List<ResponseDTO>>,
                response: Response<List<ResponseDTO>>
            ) {

                itemList = response.body()!!
                dataAdapter = DataAdapter(itemList, itemClickListener)
                recyclerView.adapter = dataAdapter
            }

            override fun onFailure(call: Call<List<ResponseDTO>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onItemClicked(responseDTO: ResponseDTO) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("response", responseDTO)
        startActivity(intent)
    }
}