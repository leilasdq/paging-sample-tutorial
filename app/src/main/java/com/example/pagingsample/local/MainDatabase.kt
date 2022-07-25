package com.example.pagingsample.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pagingsample.local.convertor.ListToStringConvertor

@Database(
    entities = [
        MoviesEntity::class
    ],
    version = 1
)
@TypeConverters(
    ListToStringConvertor::class
)
abstract class MainDatabase  : RoomDatabase() {

    abstract fun movieDao(): MoviesDao

    companion object {
        private const val databaseName = "main_db"

        fun buildDatabase(context: Context): MainDatabase {
            return Room.databaseBuilder(context, MainDatabase::class.java, databaseName)
                .build()
        }
    }
}