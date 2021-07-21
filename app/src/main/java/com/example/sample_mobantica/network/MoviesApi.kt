package com.example.sample_mobantica.network

import com.example.sample_mobantica.data.Movies
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import java.util.*

interface MoviesApi {
    @GET("json/movies.json")
    fun getMovies() : Single<Response<ArrayList<Movies>>>
}