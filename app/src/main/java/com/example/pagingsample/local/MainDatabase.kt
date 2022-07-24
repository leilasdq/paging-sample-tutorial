package com.example.pagingsample.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        MoviesEntity::class
    ],
    version = 1
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