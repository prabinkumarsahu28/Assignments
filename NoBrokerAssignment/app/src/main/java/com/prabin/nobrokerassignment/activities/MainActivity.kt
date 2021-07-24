package com.prabin.nobrokerassignment.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.prabin.nobrokerassignment.R
import com.prabin.nobrokerassignment.listeners.ItemClickListener
import com.prabin.nobrokerassignment.recyclerview.DataAdapter
import com.prabin.nobrokerassignment.roomDb.DataApplication
import com.prabin.nobrokerassignment.roomDb.DataEntity
import com.prabin.nobrokerassignment.roomDb.DataViewModel
import com.prabin.nobrokerassignment.roomDb.DataViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ItemClickListener {

    private var dataList = arrayListOf<DataEntity>()
    private lateinit var dataAdapter: DataAdapter
    var search = "Title"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shimmerFrameLayout.startShimmer()

        val application = application as DataApplication
        val repo = application.dataRepo
        val viewModelFactory = DataViewModelFactory(repo)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(DataViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        dataAdapter = DataAdapter(dataList, this)
        recyclerView.adapter = dataAdapter

        viewModel.getData().observe(this, {
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            dataList.clear()
            dataList.addAll(it)
            dataAdapter.notifyDataSetChanged()
        })

        if (dataList.isEmpty()) {
            callApi(viewModel)
        }

        itemSearch(viewModel, this)

    }

    /**
     * this function is watching text changing and providing search result according to that
     */
    private fun itemSearch(viewModel: DataViewModel, mainActivity: MainActivity) {
        etItems.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {
                search = if (input!!.isNotEmpty()) {
                    input.toString()
                } else {
                    "title"
                }
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.GONE
                viewModel.getSearch(search).observe(mainActivity, {
                    dataList.clear()
                    dataList.addAll(it)
                    dataAdapter.notifyDataSetChanged()
                })
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    /**
     * this function is used for api call
     */
    private fun callApi(viewModel: DataViewModel) {
        viewModel.getAllData(this)
    }

    /**
     * this clickListener listens item click and sends data to the next screen
     */
    override fun onItemClicked(dataEntity: DataEntity) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("dataEntity", dataEntity)
        startActivity(intent)
    }
}