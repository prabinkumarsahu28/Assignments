package com.prabin.nobrokerassignment.listeners

import com.prabin.nobrokerassignment.roomDb.DataEntity

interface ItemClickListener {
    fun onItemClicked(dataEntity: DataEntity)
}