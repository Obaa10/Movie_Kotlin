package com.test.lastkotlin.network.`interface`

import com.test.lastkotlin.model.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetMovieListInterface {
    @GET
    fun getAllMovies(@Url url: String?): Call<MoviesListResponse>
}