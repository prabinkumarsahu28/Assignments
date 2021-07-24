package com.prabin.nobrokerassignment.roomDb

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.prabin.nobrokerassignment.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepo(private val dataDao: DataDao) {
    private val apiClient = Network.getInstance().create(ApiClient::class.java)

    fun getAllData(context: Context) {
        apiClient.getData().enqueue(object : Callback<List<ResponseDTO>> {
            override fun onResponse(
                call: Call<List<ResponseDTO>>,
                response: Response<List<ResponseDTO>>
            ) {

                val itemList = response.body()
                for (i in itemList!!.indices) {
                    Log.d("prabin", itemList[i].title!!)
                    val dataEntity =
                        DataEntity(
                            i + 1,
                            itemList[i].image!!,
                            itemList[i].title!!,
                            itemList[i].subTitle!!
                        )
                    CoroutineScope(Dispatchers.IO).launch {
                        dataDao.addData(dataEntity)
                    }
                }
            }

            override fun onFailure(call: Call<List<ResponseDTO>>, t: Throwable) {
                Toast.makeText(context, "No Connection", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getData(): LiveData<List<DataEntity>> {
        return dataDao.getData()
    }

    fun getSearch(search: String): LiveData<List<DataEntity>> {
        return dataDao.getSearch(search)
    }

    fun addData(dataEntity: DataEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dataDao.addData(dataEntity)
        }
    }
}