package com.example.sample_mobantica.di.component

import android.content.Context
import com.example.sample_mobantica.di.module.ApplicationModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun provideApplicationContext(): Context
    fun provideCompoiteDisposable(): CompositeDisposable
}