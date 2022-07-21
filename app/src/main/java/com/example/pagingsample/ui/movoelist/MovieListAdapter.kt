package com.example.pagingsample.ui.movoelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.databinding.ItemListMoviesBinding
import com.example.pagingsample.model.Movies

class MovieListAdapter: PagingDataAdapter<Movies, MovieListAdapter.MoviesViewHolder>(MoviesDiffUtil()) {

    class MoviesViewHolder(
        private val binding: ItemListMoviesBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            with(binding) {
                url = item.poster
                title = item.title
                rating = item.rating
                var genreString = ""
                item.genres?.forEach {
                    genreString += "$it, "
                }
                genres = genreString.substring(0, genreString.length - 2)
                year = item.year
                description = "This was a perfect movie in ${item.year} by ${item.country}\nMake sure you will not miss it"
            }
        }
    }

    class MoviesDiffUtil: DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            ItemListMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}