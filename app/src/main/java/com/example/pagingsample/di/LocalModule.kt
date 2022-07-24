package com.example.pagingsample.di

import android.content.Context
import com.example.pagingsample.local.MainDatabase
import com.example.pagingsample.local.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): MainDatabase =
        MainDatabase.buildDatabase(appContext)

    @Provides
    fun provideMovieDao(database: MainDatabase): MoviesDao =
        database.movieDao()

}