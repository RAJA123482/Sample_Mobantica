package com.example.sample_mobantica.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sample_mobantica.data.Movies
import com.example.sample_mobantica.network.MoviesApi
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MoviesListRepository @Inject constructor(private val moviesApi: MoviesApi) {

    fun getMovies() : LiveData<ArrayList<Movies>> {
        val mutableData = MutableLiveData<ArrayList<Movies>>()
        moviesApi.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<ArrayList<Movies>>>{
                override fun onSuccess(t: Response<ArrayList<Movies>>) {
                    mutableData.value = t.body()
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    mutableData.value = ArrayList()
                    e.printStackTrace()
                }

            })

        return mutableData
    }

}