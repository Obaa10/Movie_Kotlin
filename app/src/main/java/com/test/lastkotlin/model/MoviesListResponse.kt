package com.test.lastkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviesListResponse {

    @SerializedName("results")
    @Expose
    var results: List<MovieResponse>? = null

}