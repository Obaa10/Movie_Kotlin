package com.test.lastkotlin.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso
import com.test.lastkotlin.BuildConfig
import com.test.lastkotlin.R
import com.test.lastkotlin.model.MovieDetailsResponse
import com.test.lastkotlin.network.RetrofitGetDataService
import com.test.lastkotlin.network.`interface`.GetMovieDetailsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Setup the ActionPar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val movieId = Objects.requireNonNull(intent.extras!!)
            .get(MOVIE_ID) as Int

        val movieUrl =
            "/3/movie/$movieId?api_key=6ddf1da8ede343f82786973e2dd7c457"

        fetchMovieDetails(movieUrl)
    }

    private fun fetchMovieDetails(movieUrl: String) {
        val apiService: GetMovieDetailsInterface =
            RetrofitGetDataService.retrofitInstance!!.create(
                GetMovieDetailsInterface::class.java
            )
        val call = apiService.getAllDetails(movieUrl)
        call.enqueue(object : Callback<MovieDetailsResponse?> {
            override fun onResponse(
                call: Call<MovieDetailsResponse?>,
                response: Response<MovieDetailsResponse?>
            ) {
                if (response.isSuccessful) {
                    if (BuildConfig.DEBUG && response.body() == null) {
                        error("Assertion failed")
                    }
                    val movieDetailsResponse = response.body()
                    movieDetailsResponse?.let { attachDataToView(it) }
                }
            }

            override fun onFailure(
                call: Call<MovieDetailsResponse?>,
                t: Throwable
            ) {
                Log.d("TAG", "Response = $t")
            }
        })
    }

    private fun attachDataToView(movieDetailsResponse: MovieDetailsResponse) {
        val imageView =
            findViewById<ImageView>(R.id.backdrop)
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieDetailsResponse.posterPath)
            .resize(imageView.width, 0).into(imageView)
        val movieVoteAverage = findViewById<TextView>(R.id.vote_average)
        val voteAverage =
            getString(R.string.vote_average, movieDetailsResponse.voteAverage.toString())
        movieVoteAverage.text = voteAverage
        val movieOverView = findViewById<TextView>(R.id.overview)
        val overView =
            getString(R.string.over_view, movieDetailsResponse.overview)
        movieOverView.text = overView
        val movieLanguage = findViewById<TextView>(R.id.language)
        val language =
            getString(R.string.movie_language, movieDetailsResponse.originalLanguage)
        movieLanguage.text = language
        val isAdult = findViewById<TextView>(R.id.adult)
        val adult =
            getString(R.string.is_adult, movieDetailsResponse.adult.toString())
        isAdult.text = adult
        val moviePopulate = findViewById<TextView>(R.id.Popularity)
        val popularity =
            getString(R.string.movie_popularity, movieDetailsResponse.popularity.toString())
        moviePopulate.text = popularity
        val movieRuntime = findViewById<TextView>(R.id.runtime)
        val runTime =
            getString(R.string.movie_run_time, movieDetailsResponse.runtime.toString())
        movieRuntime.text = runTime
        val movieTitle = findViewById<TextView>(R.id.detail_title)
        val title =
            getString(R.string.movie_title, movieDetailsResponse.originalTitle)
        movieTitle.text = title
    }

    companion object {
         const val MOVIE_ID = "movieId"
    }
}