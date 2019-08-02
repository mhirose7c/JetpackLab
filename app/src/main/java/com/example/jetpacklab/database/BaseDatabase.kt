package com.example.jetpacklab.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpacklab.dao.CompanyDao
import com.example.jetpacklab.entity.Company


@Database(entities = [Company::class], version = 1, exportSchema = false)
abstract class BaseDatabase: RoomDatabase(){

    abstract fun companyDao(): CompanyDao

    companion object {
        @Volatile
        private var instance: BaseDatabase? = null

        fun getInstance(context: Context): BaseDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(context, BaseDatabase::class.java, "techbooster.db").build()
        }
    }
}