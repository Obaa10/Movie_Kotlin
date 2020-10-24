package com.test.lastkotlin.datasource

import androidx.paging.PageKeyedDataSource
import com.test.lastkotlin.model.MovieResponse
import com.test.lastkotlin.model.MoviesListResponse
import com.test.lastkotlin.network.RetrofitGetDataService
import com.test.lastkotlin.network.`interface`.GetMovieListInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieSource : PageKeyedDataSource<Int, MovieResponse?>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieResponse?>
    ) {
        val service: GetMovieListInterface = RetrofitGetDataService.retrofitInstance!!.create(
            GetMovieListInterface::class.java
        )
        val call: Call<MoviesListResponse> =
            service.getAllMovies(MOVIE_URL + FIRST_PAGE.toString())
        call.enqueue(object : Callback<MoviesListResponse?> {
            override fun onResponse(
                call: Call<MoviesListResponse?>,
                response: Response<MoviesListResponse?>
            ) {
                if (response.body() != null) {
                    val movies: List<MovieResponse?> =
                            response.body()!!.results!!
                    callback.onResult(movies, null, FIRST_PAGE + 1)
                }
            }

            override fun onFailure(
                call: Call<MoviesListResponse?>,
                t: Throwable
            ) {
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieResponse?>
    ) {
        val service: GetMovieListInterface = RetrofitGetDataService.retrofitInstance!!.create(
            GetMovieListInterface::class.java
        )
        val call: Call<MoviesListResponse> =
            service.getAllMovies(MOVIE_URL + params.key.toString())
        call.enqueue(object : Callback<MoviesListResponse?> {
            override fun onResponse(
                call: Call<MoviesListResponse?>,
                response: Response<MoviesListResponse?>
            ) {
                if (response.body() != null) {
                    val movies: List<MovieResponse?> =
                        response.body()!!.results!!
                    val key: Int = if (params.key > 1) params.key - 1 else 0
                    callback.onResult(movies, key)
                }
            }

            override fun onFailure(
                call: Call<MoviesListResponse?>,
                t: Throwable
            ) {
            }
        })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieResponse?>
    ) {
        val service: GetMovieListInterface = RetrofitGetDataService.retrofitInstance!!.create(
            GetMovieListInterface::class.java
        )
        val call: Call<MoviesListResponse> =
            service.getAllMovies(MOVIE_URL + params.key.toString())
        call.enqueue(object : Callback<MoviesListResponse?> {
            override fun onResponse(
                call: Call<MoviesListResponse?>,
                response: Response<MoviesListResponse?>
            ) {
                if (response.body() != null) {
                    val movies: List<MovieResponse?> =
                        response.body()!!.results!!
                    callback.onResult(movies, params.key + 1)
                }
            }

            override fun onFailure(
                call: Call<MoviesListResponse?>,
                t: Throwable
            ) {
            }
        })
    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val MOVIE_URL =
            "/3/discover/movie?api_key=6ddf1da8ede343f82786973e2dd7c457&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page="
        const val LIST_SIZE = 20
    }
}
