package com.example.sample_mobantica.di.component

import com.example.sample_mobantica.di.PerActivity
import com.example.sample_mobantica.ui.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}