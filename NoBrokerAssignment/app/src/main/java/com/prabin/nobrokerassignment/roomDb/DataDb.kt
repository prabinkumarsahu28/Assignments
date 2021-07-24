package com.prabin.nobrokerassignment.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataEntity::class], version = 1)
abstract class DataDb : RoomDatabase() {
    abstract fun getDataDao(): DataDao

    companion object {
        private var INSTANCE: DataDb? = null

        fun getContextDb(context: Context): DataDb {
            return if (INSTANCE == null) {
                val builder =
                    Room.databaseBuilder(context.applicationContext, DataDb::class.java, "dataDB")
                        .fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }
}