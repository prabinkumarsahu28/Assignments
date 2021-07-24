package com.prabin.nobrokerassignment.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabin.nobrokerassignment.listeners.ItemClickListener
import com.prabin.nobrokerassignment.roomDb.DataEntity
import kotlinx.android.synthetic.main.item_layout.view.*

class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setData(dataEntity: DataEntity, itemClickListener: ItemClickListener) {
        view.apply {
            Glide.with(this).load(dataEntity.image).into(ivItem)
            tvTitle.text = dataEntity.title
            tvDesc.text = dataEntity.subTitle
            clItem.setOnClickListener {
                itemClickListener.onItemClicked(dataEntity)
            }
        }
    }
}