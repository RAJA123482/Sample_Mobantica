package com.example.sample_mobantica.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ApplicationModule(var application: Application) {
    @Provides
    fun provideApplicationContext(): Context = application

    @Provides
    fun provideDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}
