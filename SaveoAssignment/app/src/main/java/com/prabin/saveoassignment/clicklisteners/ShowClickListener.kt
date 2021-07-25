package com.prabin.saveoassignment.clicklisteners

import android.widget.ImageView
import com.prabin.saveoassignment.model.ResponseModel
import com.prabin.saveoassignment.model.ShowModel

interface ShowClickListener {

    fun onShowClicked(showModel: ShowModel, ivImgShow: ImageView)
    fun onMovieClicked(responseModel: ResponseModel, ivHorImg: ImageView)
}