package com.prabin.saveoassignment.clicklisteners

import com.prabin.saveoassignment.model.ResponseModel
import com.prabin.saveoassignment.model.ShowModel

interface ShowClickListener {

    fun onShowClicked(showModel: ShowModel)
    fun onMovieClicked(responseModel: ResponseModel)
}