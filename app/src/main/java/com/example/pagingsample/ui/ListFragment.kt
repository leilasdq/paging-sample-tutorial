package com.example.pagingsample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.pagingsample.R

@AndroidEntryPoint
class ListFragment: Fragment(R.layout.fragment_kist) {

    private val TAG = "leila_Movies"
    private val viewModel: ListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieList.observe(viewLifecycleOwner) {
            it.forEach {
                Log.d(TAG, "onCreate: list item = $it")
            }
        }
    }
}