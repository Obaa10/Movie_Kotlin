package com.test.lastkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse {

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Float? = null
}