package com.prabin.nobrokerassignment.roomDb

import android.app.Application

class DataApplication : Application() {
    private val dataDao by lazy {
        val database = DataDb.getContextDb(this)
        database.getDataDao()
    }

    val dataRepo by lazy {
        DataRepo(dataDao)
    }
}