package com.example.sample_mobantica.di.component

import com.example.sample_mobantica.di.PerActivity
import com.example.sample_mobantica.di.module.FragmentModule
import com.example.sample_mobantica.ui.fragment.MoviesFragment
import dagger.Component

@PerActivity
@Component(modules = [FragmentModule::class], dependencies = [ApplicationComponent::class])
interface FragmentComponent {
    fun inject(moviesFragment: MoviesFragment)
}