package com.example.movieplex.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieplex.R
import com.example.movieplex.activity.MainActivity
import com.example.movieplex.activity.MovieDetailsActivity
import com.example.movieplex.activity.TvDetailsActivity
import com.example.movieplex.adapter.DefaultTvAdapter
import com.example.movieplex.adapter.DiscoverMovieAdapter
import com.example.movieplex.adapter.NowPlayingMovieAdapter
import com.example.movieplex.adapter.PopularTvAdapter
import com.example.movieplex.databinding.FragmentHomeBinding
import com.example.movieplex.pojo.Movie
import com.example.movieplex.pojo.Tv
import com.example.movieplex.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: HomeViewModel

    private lateinit var discoverMovieAdapter: DiscoverMovieAdapter
    private lateinit var upComingMovieAdapter: DiscoverMovieAdapter
    private lateinit var nowPlayingMovieAdapter: NowPlayingMovieAdapter

    private lateinit var popularTvAdapter: PopularTvAdapter
    private lateinit var topRatedTvAdapter: DefaultTvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        discoverMovieAdapter = DiscoverMovieAdapter()
        upComingMovieAdapter = DiscoverMovieAdapter()
        nowPlayingMovieAdapter = NowPlayingMovieAdapter()

        popularTvAdapter = PopularTvAdapter()
        topRatedTvAdapter = DefaultTvAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareDiscoverMovieRecyclerView()
        prepareUpComingMovieRecyclerView()
        prepareNowPlayingMovieViewPager2()

        preparePopularTvRecyclerView()
        prepareTopRatedTvRecyclerView()

        viewModel.getNowPlaying()
        observeNowPlayingListLiveData()
        onNowPlayingItemClick()

        viewModel.getUpComing()
        observeUpComingListLiveData()
        upComingItemClick()

        viewModel.getDiscover()
        observeDiscoverListLiveData()
        onDiscoverItemClick()

        viewModel.getPopularTv()
        observePopularTvListLiveData()
        popularTvItemClick()

        viewModel.getTopRatedTv()
        observeTopRatedTvListLiveData()
        onTopRatedTvItemClick()

        onExploreMoreButtonClick()
    }

    private fun onTopRatedTvItemClick() {
        topRatedTvAdapter.onItemClick = {
            val intent = Intent(activity, TvDetailsActivity::class.java)
            intent.putExtra("Tv_Id", it.id)
            startActivity(intent)
        }
    }

    private fun observeTopRatedTvListLiveData() {
        viewModel.topRatedTvListLiveData.observe(viewLifecycleOwner){
            topRatedTvAdapter.setTv(it as ArrayList<Tv>)
        }
    }

    private fun prepareTopRatedTvRecyclerView() {
        binding.rvTopRatedTv.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedTvAdapter
        }
    }

    private fun popularTvItemClick() {
        popularTvAdapter.onItemClickTv = {
            val intent = Intent(activity, TvDetailsActivity::class.java)
            intent.putExtra("Tv_Id", it.id)
            startActivity(intent)
        }
    }

    private fun observePopularTvListLiveData() {
        viewModel.popularTvListLiveData.observe(viewLifecycleOwner){
            popularTvAdapter.setTv(it as ArrayList<Tv>)
        }
    }

    private fun preparePopularTvRecyclerView() {
        binding.vpPopularTv.apply {
            adapter = popularTvAdapter
            setInfinite(true)
            setAlpha(true)
            set3DItem(true)
            setIntervalRatio(0.6f)
        }
    }

    private fun onExploreMoreButtonClick() {
        binding.btnExploreMoreMovies.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_exploreFragment)
        }
    }

    private fun onNowPlayingItemClick() {
        nowPlayingMovieAdapter.onItemClick = {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("Movie_Id", it.id)
            startActivity(intent)

        }
    }

    private fun upComingItemClick() {
        upComingMovieAdapter.onItemClick = {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("Movie_Id", it.id)
            startActivity(intent)
        }
    }

    private fun onDiscoverItemClick() {
        discoverMovieAdapter.onItemClick = {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("Movie_Id", it.id)

            startActivity(intent)
        }
    }

    private fun prepareNowPlayingMovieViewPager2() {
        binding.vpNowPlaying.apply {
            adapter = nowPlayingMovieAdapter
            setInfinite(true)
            setAlpha(true)
            set3DItem(true)
            setIntervalRatio(0.6f)
        }
    }

    private fun observeNowPlayingListLiveData() {
        viewModel.nowPlayingListLivedata.observe(viewLifecycleOwner) {
            nowPlayingMovieAdapter.setMovie(it as ArrayList<Movie>)

            val bundle = Bundle()
            bundle.putInt("Movie_Id", it[0].id)
            requireFragmentManager().setFragmentResult("Movie_Id", bundle)
        }
    }

    private fun prepareUpComingMovieRecyclerView() {
        binding.rvComingSoon.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = upComingMovieAdapter
        }
    }

    private fun observeUpComingListLiveData() {
        viewModel.upComingListLiveData.observe(viewLifecycleOwner){
            upComingMovieAdapter.setMovie(it as ArrayList<Movie>)
        }
    }

    private fun observeDiscoverListLiveData() {
        viewModel.discoverListLiveData.observe(viewLifecycleOwner){
            discoverMovieAdapter.setMovie(it as ArrayList<Movie>)
        }
    }

    private fun prepareDiscoverMovieRecyclerView() {
        binding.rvDiscover.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = discoverMovieAdapter
        }
    }

}