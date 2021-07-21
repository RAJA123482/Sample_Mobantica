package com.example.sample_mobantica.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sample_mobantica.R
import com.example.sample_mobantica.data.Movies
import com.example.sample_mobantica.databinding.ItemMoviesBinding

class MoviesAdapter (val fragment: Fragment, val arrayList: ArrayList<Movies>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesListHolder(binding)
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val moviesHolder = holder as MoviesListHolder
        moviesHolder.bindItems(model = arrayList[position])
    }

    inner class MoviesListHolder(val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root){
        fun bindItems(model: Movies) {
            val callback = fragment as MoviesCallback

            binding.apply {
                movies = model
                moviesAdapter = this@MoviesAdapter
            }

            Glide.with(fragment)
                .load(model.image)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(binding.ivSticker)

            itemView.setOnClickListener {
                callback.movieClicked(model.title ?: "movie")
            }
        }
    }

    fun addItems(arrayList: ArrayList<Movies>) {
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
        notifyDataSetChanged()
    }

    interface MoviesCallback {
        fun movieClicked(title: String)
    }
}