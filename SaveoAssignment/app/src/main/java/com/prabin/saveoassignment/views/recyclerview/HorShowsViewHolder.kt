package com.prabin.saveoassignment.views.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabin.saveoassignment.clicklisteners.ShowClickListener
import com.prabin.saveoassignment.model.ResponseModel
import kotlinx.android.synthetic.main.hor_item_layout.view.*

class HorShowsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(responseModel: ResponseModel, showClickListener: ShowClickListener) {
        view.apply {
            Glide.with(this).load(responseModel.show?.image!!.original).into(ivHorImg)
            ivHorImg.setOnClickListener {
                showClickListener.onMovieClicked(responseModel, ivHorImg)
            }
        }
    }
}