package com.example.movieplex.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieplex.activity.MainActivity
import com.example.movieplex.activity.MovieDetailsActivity
import com.example.movieplex.adapter.DiscoverMovieAdapter
import com.example.movieplex.databinding.FragmentExploreBinding
import com.example.movieplex.pojo.Movie
import com.example.movieplex.viewmodel.HomeViewModel

class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding

    private lateinit var viewModel: HomeViewModel

    private lateinit var recommendedMovieAdapter: DiscoverMovieAdapter
    private lateinit var popularMovieAdapter: DiscoverMovieAdapter
    private lateinit var topRatedMovieAdapter: DiscoverMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)

        recommendedMovieAdapter = DiscoverMovieAdapter()
        popularMovieAdapter = DiscoverMovieAdapter()
        topRatedMovieAdapter = DiscoverMovieAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecommendationRecyclerView()
        preparePopularMovieRecyclerView()
        prepareTopRatedMovieRecyclerView()

        getRecommendation()
        observeRecommendationListLiveData()
        onRecommendationItemClick()

        viewModel.getPopular()
        observePopularListLiveData()
        onPopularItemClick()

        viewModel.getTopRated()
        observeTopRatedListLiveData()
        onTopRatedItemClick()

    }

    private fun onTopRatedItemClick() {
        topRatedMovieAdapter.onItemClick = {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("Movie_Id", it.id)
            startActivity(intent)
        }
    }

    private fun observeTopRatedListLiveData() {
        viewModel.topRatedListLiveData.observe(viewLifecycleOwner){
            topRatedMovieAdapter.setMovie(it as ArrayList<Movie>)
        }
    }

    private fun prepareTopRatedMovieRecyclerView() {
        binding.rvTopRated.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedMovieAdapter
        }
    }

    private fun preparePopularMovieRecyclerView() {
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMovieAdapter
        }
    }

    private fun onPopularItemClick() {
        popularMovieAdapter.onItemClick = {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("Movie_Id", it.id)
            startActivity(intent)
        }
    }

    private fun observePopularListLiveData() {
        viewModel.popularListLiveData.observe(viewLifecycleOwner){
            popularMovieAdapter.setMovie(it as ArrayList<Movie>)
        }
    }

    private fun onRecommendationItemClick() {
        recommendedMovieAdapter.onItemClick = {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("Movie_Id", it.id)
            startActivity(intent)
        }
    }

    private fun observeRecommendationListLiveData() {
        viewModel.recommendationListLiveData.observe(viewLifecycleOwner) {
            recommendedMovieAdapter.setMovie(it as ArrayList<Movie>)
        }
    }

    private fun getRecommendation() {
        requireFragmentManager().setFragmentResultListener("Movie_Id", this) { _, result ->
            val movieId = result.getInt("Movie_Id")
            viewModel.getRecommendation(movieId)
        }
    }

    private fun prepareRecommendationRecyclerView() {
        binding.rvRecommended.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendedMovieAdapter
        }
    }

}