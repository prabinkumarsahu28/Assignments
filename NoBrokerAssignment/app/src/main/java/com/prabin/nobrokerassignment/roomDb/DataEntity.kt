package com.prabin.nobrokerassignment.roomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "data_table")
data class DataEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "subTitle") var subTitle: String,
) : Serializable