package com.prabin.saveoassignment.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabin.saveoassignment.R
import com.prabin.saveoassignment.clicklisteners.ShowClickListener
import com.prabin.saveoassignment.model.ResponseModel

class HorShowsAdapter(
    private val responseList: List<ResponseModel>,
    private val showClickListener: ShowClickListener
) : RecyclerView.Adapter<HorShowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorShowsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.hor_item_layout, parent, false)
        return HorShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorShowsViewHolder, position: Int) {
        holder.setData(responseList[position], showClickListener)
    }

    override fun getItemCount(): Int {
        return responseList.size
    }
}