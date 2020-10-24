package com.test.lastkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDetailsResponse {
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    val overview: String? = null

    @SerializedName("popularity")
    @Expose
    val popularity: Float? = null

    @SerializedName("runtime")
    @Expose
    val runtime: Int? = null

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Float? = null

    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null

}

