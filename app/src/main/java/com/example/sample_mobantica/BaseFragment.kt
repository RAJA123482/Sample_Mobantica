package com.example.sample_mobantica

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sample_mobantica.di.component.DaggerFragmentComponent
import com.example.sample_mobantica.di.component.FragmentComponent
import com.example.sample_mobantica.di.module.FragmentModule

open class BaseFragment : Fragment() {
    lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule(this))
            .applicationComponent(((activity?.applicationContext) as MovieApplication).applicationComponent)
            .build()
    }
}