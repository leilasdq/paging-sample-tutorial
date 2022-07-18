package com.example.pagingsample.ui.movoelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.databinding.ItemListMoviesBinding
import com.example.pagingsample.model.Movies

class MovieListAdapter: ListAdapter<Movies, MovieListAdapter.MoviesViewHolder>(MoviesDiffUtil()) {

    class MoviesViewHolder(
        private val binding: ItemListMoviesBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            with(binding) {
                url = item.poster
                title = item.title
                rating = item.rating
                var genreString = ""
                item.genres.forEach {
                    genreString += "$it, "
                }
                genres = genreString.substring(0, genreString.length - 1)
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
            ItemListMoviesBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

/*
* class IncomeHistoryAdapter(val lifecycleOwner: LifecycleOwner) :
    PagingDataAdapter<IncomeHistory, IncomeHistoryAdapter.IncomeHistoryItemViewHolder>(
        IncomesHistoryItemDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeHistoryItemViewHolder =
        IncomeHistoryItemViewHolder(
            ItemIncomeHistoryBinding.inflate(parent.context.layoutInflater, parent, false)
                .apply { lifecycleOwner = this@IncomeHistoryAdapter.lifecycleOwner },
        )

    override fun onBindViewHolder(holder: IncomeHistoryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class IncomeHistoryItemViewHolder(
        private val binding: ItemIncomeHistoryBinding,
    ) : ExtendedViewHolder<IncomeHistory?>(binding.root) {

        override fun bind(item: IncomeHistory?) {
            if (item != null) {
                binding.run {
                    currency = item.currency
                    amount = item.amount
                    createdAt = item.createdAt
                }
            } else {
                binding.run {
                    currency = null
                    amount = null
                    createdAt = null
                }
            }
        }
    }

    object IncomesHistoryItemDiffCallback : DiffUtil.ItemCallback<IncomeHistory>() {
        override fun areItemsTheSame(oldItem: IncomeHistory, newItem: IncomeHistory): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: IncomeHistory, newItem: IncomeHistory): Boolean =
            oldItem == newItem
    }

}
* */