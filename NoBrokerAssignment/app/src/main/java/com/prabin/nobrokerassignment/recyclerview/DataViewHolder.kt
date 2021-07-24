package com.prabin.nobrokerassignment.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabin.nobrokerassignment.data.ResponseDTO
import com.prabin.nobrokerassignment.listeners.ItemClickListener
import kotlinx.android.synthetic.main.item_layout.view.*

class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setData(responseDTO: ResponseDTO, itemClickListener: ItemClickListener) {
        view.apply {
            Glide.with(this).load(responseDTO.image).into(ivItem)
            tvTitle.text = responseDTO.title
            tvDesc.text = responseDTO.subTitle
            clItem.setOnClickListener {
                itemClickListener.onItemClicked(responseDTO)
            }
        }
    }
}