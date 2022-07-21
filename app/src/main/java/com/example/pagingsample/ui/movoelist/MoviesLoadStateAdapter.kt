package com.example.pagingsample.ui.movoelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.databinding.ViewPagingLoadStateBinding

class MoviesLoadStateAdapter(
    val retryFunction: () -> Unit
) : LoadStateAdapter<MoviesLoadStateAdapter.MoviesLoadStateViewHolder>() {

    class MoviesLoadStateViewHolder(
        private val binding: ViewPagingLoadStateBinding,
        private val retryFunction: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun binding(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                errorMsg.isVisible = loadState is LoadState.Error
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.text = if (loadState is LoadState.Error) loadState.error.message else ""
                retryButton.setOnClickListener {
                    retryFunction.invoke()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MoviesLoadStateViewHolder, loadState: LoadState) {
        holder.binding(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MoviesLoadStateViewHolder =
        MoviesLoadStateViewHolder(
            ViewPagingLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retryFunction
        )
}