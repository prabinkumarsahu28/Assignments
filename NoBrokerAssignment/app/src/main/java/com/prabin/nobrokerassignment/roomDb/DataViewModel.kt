package com.prabin.nobrokerassignment.roomDb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class DataViewModel(private val dataRepo: DataRepo) : ViewModel() {
    fun getData(): LiveData<List<DataEntity>> {
        return dataRepo.getData()
    }

    fun getSearch(search:String): LiveData<List<DataEntity>> {
        return dataRepo.getSearch(search)
    }

    fun addData(dataEntity: DataEntity) {
        dataRepo.addData(dataEntity)
    }
}