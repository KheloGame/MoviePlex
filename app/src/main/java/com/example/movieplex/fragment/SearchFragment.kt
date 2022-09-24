package com.example.movieplex.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieplex.activity.MainActivity
import com.example.movieplex.activity.MovieDetailsActivity
import com.example.movieplex.adapter.SearchMovieAdapter
import com.example.movieplex.databinding.FragmentSearchBinding
import com.example.movieplex.pojo.Movie
import com.example.movieplex.viewmodel.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private lateinit var viewModel: HomeViewModel

    private lateinit var searchMovieAdapter: SearchMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        searchMovieAdapter = SearchMovieAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareSearchMovieRecyclerView()

        getSearchedMovie()
        observeSearchListLiveData()
        onSearchItemClick()
    }

    private fun onSearchItemClick() {
        searchMovieAdapter.onItemClick = {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("Movie_Id", it.id)
            startActivity(intent)
        }
    }

    private fun prepareSearchMovieRecyclerView() {
        binding.rvSearch.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = searchMovieAdapter
        }
    }

    private fun observeSearchListLiveData() {
        viewModel.searchListLiveData.observe(viewLifecycleOwner){
            searchMovieAdapter.setMovie(it as ArrayList<Movie>)
        }
    }

    private fun getSearchedMovie() {
        var searchJob: Job? = null
        binding.svSearchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.getSearchedMovie(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchJob?.cancel()
                    searchJob = lifecycleScope.launch{
                        delay(500)
                        viewModel.getSearchedMovie(query)
                    }
                }
                return false
            }
        })
    }
}