package com.prabin.nobrokerassignment.roomDb

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepo(private val dataDao: DataDao) {
    fun getData(): LiveData<List<DataEntity>> {
        return dataDao.getData()
    }

    fun addData(dataEntity: DataEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dataDao.addData(dataEntity)
        }
    }
}