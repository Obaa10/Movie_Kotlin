package com.test.lastkotlin.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.test.lastkotlin.model.MovieResponse


class MovieSourceFactory : DataSource.Factory<Int, MovieResponse>() {
    var movieSourceMutableLiveData = MutableLiveData<MovieSource>()
    override fun create(): MovieSource {
        val movieSource = MovieSource()
        movieSourceMutableLiveData.postValue(movieSource)
        return movieSource
    }
}
