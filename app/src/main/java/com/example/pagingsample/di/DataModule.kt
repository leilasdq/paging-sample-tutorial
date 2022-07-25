package com.example.pagingsample.di

import com.example.pagingsample.data.MoviesRepository
import com.example.pagingsample.data.MoviesRepositoryImpl
import com.example.pagingsample.local.MoviesDao
import com.example.pagingsample.network.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesService: MoviesService, moviesDao: MoviesDao): MoviesRepository =
        MoviesRepositoryImpl(moviesService, moviesDao)
}