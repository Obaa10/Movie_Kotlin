package com.test.lastkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.test.lastkotlin.datasource.MovieSource
import com.test.lastkotlin.datasource.MovieSourceFactory
import com.test.lastkotlin.model.MovieResponse


class MovieListViewModel : ViewModel() {
    var pagedListLiveData: LiveData<PagedList<MovieResponse>>
    private val movieSourceLiveData: LiveData<MovieSource>

    init {
        val factory = MovieSourceFactory()
        movieSourceLiveData = factory.movieSourceMutableLiveData
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MovieSource.LIST_SIZE)
            .build()
        pagedListLiveData = LivePagedListBuilder<Int, MovieResponse>(factory, config).build()
    }
}
