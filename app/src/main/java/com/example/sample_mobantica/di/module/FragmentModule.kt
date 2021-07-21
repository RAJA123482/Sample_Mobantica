package com.example.sample_mobantica.di.module

import androidx.fragment.app.Fragment
import com.example.sample_mobantica.AppConstants
import com.example.sample_mobantica.network.MoviesApi
import com.example.sample_mobantica.ui.adapter.MoviesAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class FragmentModule(val fragment: Fragment) {
    @Provides
    fun provideMoviesListApi(): MoviesApi {
        var okHttpClient = OkHttpClient.Builder()
            .connectTimeout((1000 * 60).toLong(), java.util.concurrent.TimeUnit.MILLISECONDS)
            .readTimeout((1000 * 60).toLong(), java.util.concurrent.TimeUnit.MILLISECONDS)
            .writeTimeout((1000 * 60).toLong(), java.util.concurrent.TimeUnit.MILLISECONDS)

        val retrofit = Retrofit.Builder().client(okHttpClient.build()).baseUrl(AppConstants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    fun provideMoviesAdapter() : MoviesAdapter {
        return MoviesAdapter(fragment = fragment, arrayList = ArrayList())
    }
}