package com.prabin.nobrokerassignment.listeners

import com.prabin.nobrokerassignment.data.ResponseDTO

interface ItemClickListener {
    fun onItemClicked(responseDTO: ResponseDTO)
}