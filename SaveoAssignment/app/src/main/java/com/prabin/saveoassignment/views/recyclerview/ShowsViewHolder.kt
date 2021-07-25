package com.prabin.saveoassignment.views.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabin.saveoassignment.clicklisteners.ShowClickListener
import com.prabin.saveoassignment.model.ShowModel
import kotlinx.android.synthetic.main.shows_item_layout.view.*

class ShowsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(showModel: ShowModel, showClickListener: ShowClickListener) {
        view.apply {
            Glide.with(this).load(showModel.image?.original).into(ivImgShow)
            ivImgShow.setOnClickListener {
                showClickListener.onShowClicked(showModel, ivImgShow)
            }
        }
    }
}