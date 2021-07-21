package com.example.sample_mobantica

import android.app.Application
import com.example.sample_mobantica.di.component.ApplicationComponent
import com.example.sample_mobantica.di.component.DaggerApplicationComponent
import com.example.sample_mobantica.di.module.ApplicationModule

class MovieApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        //setup dagger
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
    }
}