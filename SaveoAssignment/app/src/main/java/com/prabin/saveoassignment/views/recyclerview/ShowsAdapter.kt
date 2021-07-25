package com.prabin.saveoassignment.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabin.saveoassignment.R
import com.prabin.saveoassignment.clicklisteners.ShowClickListener
import com.prabin.saveoassignment.model.ResponseModel
import com.prabin.saveoassignment.model.ShowModel
import com.prabin.saveoassignment.models.ShowsModel

class ShowsAdapter(
    private val responseList: List<ShowModel>,
    private val showClickListener: ShowClickListener
) : RecyclerView.Adapter<ShowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shows_item_layout, parent, false)
        return ShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        holder.setData(responseList[position], showClickListener)
    }

    override fun getItemCount(): Int {
        return responseList.size
    }
}