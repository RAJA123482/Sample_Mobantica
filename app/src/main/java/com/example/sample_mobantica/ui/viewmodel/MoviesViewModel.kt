package com.example.sample_mobantica.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sample_mobantica.data.Movies
import com.example.sample_mobantica.data.repositories.MoviesListRepository
import java.util.*
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val movieInstance: MoviesListRepository) : ViewModel() {

    var moviesList: LiveData<ArrayList<Movies>>? = null

    fun getMovies() {
        moviesList = movieInstance.getMovies()
    }

}