package com.test.lastkotlin.network.`interface`

import com.test.lastkotlin.model.MovieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetMovieDetailsInterface {
    @GET
    fun getAllDetails(@Url movieUrl: String): Call<MovieDetailsResponse>
}
