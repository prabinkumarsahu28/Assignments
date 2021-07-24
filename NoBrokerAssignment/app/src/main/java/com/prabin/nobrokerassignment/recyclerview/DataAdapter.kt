package com.prabin.nobrokerassignment.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabin.nobrokerassignment.R
import com.prabin.nobrokerassignment.listeners.ItemClickListener
import com.prabin.nobrokerassignment.roomDb.DataEntity

class DataAdapter(
    private val dataList: List<DataEntity>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.setData(dataList[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        Log.d("prabin", dataList.size.toString())
        return dataList.size
    }
}