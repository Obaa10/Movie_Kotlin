package com.test.lastkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.test.lastkotlin.R
import com.test.lastkotlin.adapter.MoviePagedListAdapter
import com.test.lastkotlin.model.MovieResponse
import com.test.lastkotlin.viewmodel.MovieListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.movie_recycler)
        val movieAdapter = MoviePagedListAdapter()
        val movieViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        movieViewModel.pagedListLiveData.observe(
            this,
            Observer<PagedList<MovieResponse>> { movies -> movieAdapter.submitList(movies) })
        recyclerView.adapter = movieAdapter
    }
}







