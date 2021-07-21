package com.example.sample_mobantica.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample_mobantica.BaseFragment
import com.example.sample_mobantica.R
import com.example.sample_mobantica.ui.adapter.MoviesAdapter
import com.example.sample_mobantica.ui.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import java.sql.DriverManager.println
import javax.inject.Inject

class MoviesFragment: BaseFragment(), MoviesAdapter.MoviesCallback {
    @Inject
    lateinit var moviesViewModel: MoviesViewModel

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentComponent.inject(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        rcv_movies.apply {
            layoutManager = LinearLayoutManager(this@MoviesFragment.activity)
            adapter = moviesAdapter
        }

        srl_movies.setOnRefreshListener {
            performNetworkCall()
        }

        performNetworkCall()
    }

    private fun performNetworkCall() {
        srl_movies.isRefreshing = true

        val connectivityManage = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManage.activeNetworkInfo
        networkInfo?.let {
            if(it.isConnected) {
                moviesViewModel.getMovies()
                moviesViewModel.moviesList?.observe(viewLifecycleOwner, Observer {
                    if(it?.size!! > 0) {
                        rcv_movies.visibility = View.VISIBLE
                        tv_empty.visibility = View.GONE
                        moviesAdapter.addItems(it)
                    } else {
                        rcv_movies.visibility = View.GONE
                        tv_empty.visibility = View.VISIBLE
                    }
                    if(srl_movies.isRefreshing) {
                        srl_movies.isRefreshing = false
                    }
                })
            } else {
                Toast.makeText(context, "Please check internet connection...", Toast.LENGTH_LONG).show()
            }
        } ?: Toast.makeText(context, "No network available...", Toast.LENGTH_LONG).show()
    }

    override fun movieClicked(title: String) {
        print("$title")
    }
}