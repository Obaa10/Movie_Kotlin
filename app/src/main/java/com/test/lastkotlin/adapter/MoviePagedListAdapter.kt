package com.test.lastkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.lastkotlin.R
import com.test.lastkotlin.activity.DetailsActivity
import com.test.lastkotlin.model.MovieResponse


class MoviePagedListAdapter :
    PagedListAdapter<MovieResponse, MoviePagedListAdapter.ViewHolder>(USER_COMPARATOR) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val movie: MovieResponse? = getItem(position)
        if (movie != null) {
            holder.create(movie)
            holder.itemView.setOnClickListener {
                val intent = Intent(it.context, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.MOVIE_ID, movie.id)
                it.context.startActivity(intent)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var movieName: TextView = view.findViewById(R.id.movie_name)
        private var movieVote: TextView = view.findViewById(R.id.movie_vote)
        private var movieRealisedDate: TextView = view.findViewById(R.id.movie_realised_data)
        private var movieImage: ImageView = view.findViewById(R.id.movie_image)
        fun create(movieResponse: MovieResponse) {
            movieName.text = movieResponse.title
            movieRealisedDate.text = movieResponse.releaseDate
            movieVote.text = movieResponse.voteAverage.toString()
            Picasso.get().load("https://image.tmdb.org/t/p/w185" + movieResponse.posterPath)
                .into(movieImage)
        }
    }

    companion object {
        private val USER_COMPARATOR: DiffUtil.ItemCallback<MovieResponse> =
            object : DiffUtil.ItemCallback<MovieResponse>() {
                override fun areItemsTheSame(
                    oldItem: MovieResponse,
                    newItem: MovieResponse
                ): Boolean {
                    return oldItem.id!! == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MovieResponse,
                    newItem: MovieResponse
                ): Boolean {
                    return oldItem.adult!! == newItem.adult
                }
            }
    }
}

