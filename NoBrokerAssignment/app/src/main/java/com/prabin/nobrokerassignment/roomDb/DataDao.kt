package com.prabin.nobrokerassignment.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {
    @Query("SELECT * FROM data_table")
    fun getData(): LiveData<List<DataEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addData(dataEntity: DataEntity)

    @Query("SELECT * FROM data_table WHERE title LIKE '%' || :search || '%' OR subTitle LIKE '%' || :search || '%'")
    fun getSearch(search: String): LiveData<List<DataEntity>>
}