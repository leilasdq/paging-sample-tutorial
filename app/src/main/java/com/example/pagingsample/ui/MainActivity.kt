package com.example.pagingsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.pagingsample.R
import com.example.pagingsample.data.MoviesRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var repo: MoviesRepository
    private val TAG = "leila_Movies"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenCreated {
            repo.getAllMoviesByPage().collectLatest {
                it.forEach {
                    Log.d(TAG, "onCreate: list item = $it")
                }
            }
        }
    }
}