package com.example.movieplex.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieplex.adapter.DiscoverMovieAdapter
import com.example.movieplex.adapter.ImageTvAdapter
import com.example.movieplex.adapter.ReviewAdapter
import com.example.movieplex.databinding.ActivityMovieDetailsBinding
import com.example.movieplex.pojo.Movie
import com.example.movieplex.pojo.MovieDetails
import com.example.movieplex.pojo.images.Backdrop
import com.example.movieplex.pojo.review.Review
import com.example.movieplex.util.Constants.URL_IMG
import com.example.movieplex.viewmodel.MovieViewModel
import com.google.android.material.chip.Chip
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    private lateinit var viewModel: MovieViewModel

    private var movieId by Delegates.notNull<Int>()

    private lateinit var imageMovieAdapter: ImageTvAdapter
    private lateinit var reviewMovieAdapter: ReviewAdapter
    private lateinit var similarMovieAdapter: DiscoverMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        imageMovieAdapter = ImageTvAdapter()
        reviewMovieAdapter = ReviewAdapter()
        similarMovieAdapter = DiscoverMovieAdapter()

        prepareImageMovieRecyclerView()
        prepareReviewMovieRecyclerView()
        prepareSimilarMovieRecyclerView()

        getMovieId()

        viewModel.getMovieDetails(movieId)
        observeMovieDetailsLiveData()

        viewModel.getImagesMovie(movieId)
        observeImagesMovieListLiveData()

        viewModel.getReviewMovie(movieId)
        observeReviewMovieListLiveData()

        viewModel.getSimilarMovie(movieId)
        observeSimilarMovieListLiveData()
        similarMovieItemClick()

        onFabBackClick()
    }

    private fun onFabBackClick() {
        binding.fabBack.setOnClickListener {
            finish()
        }
    }

    private fun observeReviewMovieListLiveData() {
        viewModel.reviewMovieLiveData.observe(this){
            reviewMovieAdapter.setReviewList(it as ArrayList<Review>)
        }
    }

    private fun prepareReviewMovieRecyclerView() {
        binding.rvReview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = reviewMovieAdapter
        }
    }

    private fun observeImagesMovieListLiveData() {
        viewModel.imagesMovieLiveData.observe(this){
            imageMovieAdapter.setImage(it as ArrayList<Backdrop>)
        }
    }

    private fun prepareImageMovieRecyclerView() {
        binding.rvImages.apply {
            adapter = imageMovieAdapter
            setInfinite(true)
            setAlpha(false)
            set3DItem(false)
            setIsScrollingEnabled(true)
            setFlat(true)
        }
    }

    private fun prepareSimilarMovieRecyclerView() {
        binding.rvSimilarTv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = similarMovieAdapter
        }
    }

    private fun similarMovieItemClick() {
        similarMovieAdapter.onItemClick = {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("Movie_Id", it.id)
            startActivity(intent)
        }
    }

    private fun observeSimilarMovieListLiveData() {
        viewModel.similarMovieListLiveData.observe(this){
            similarMovieAdapter.setMovie(it as ArrayList<Movie>)
        }
    }

    private fun observeMovieDetailsLiveData() {
        viewModel.movieDetailsLiveData.observe(this, object : Observer<MovieDetails>{
            override fun onChanged(it: MovieDetails?) {
                Glide.with(applicationContext)
                    .load(URL_IMG+it!!.backdropPath)
                    .into(binding.imgBackdrop)

                Glide.with(applicationContext)
                    .load(URL_IMG+it.posterPath)
                    .into(binding.imgPoster)

                binding.tvTitle.text = it.title
                if(it.tagline.isNotEmpty()){
                    binding.tvTagline.text = it.tagline
                }else{
                    binding.tvTagline.text = "N/A"
                }

                val outputDate = SimpleDateFormat("MM/yyyy", Locale.US)
                    .format(SimpleDateFormat("yyyy-MM-dd", Locale.US)
                        .parse(it.releaseDate) as Date)

                if(it.releaseDate.isNotEmpty()){
                    binding.tvReleaseDate.text = "Release: $outputDate"
                }else{
                    "N/A"
                }
                binding.tvOverview.text = it.overview
                it.genres.forEach {
                    addChip(it.name)
                }
                binding.tvAvgRating.text = "Rating: ${it.voteAverage.toInt()}/10"
            }

        })
    }

    fun addChip(text: String){
        val chip = Chip(this)
        chip.text = text
        binding.cgGenre.addView(chip)
    }

    private fun getMovieId() {
        val intent = intent
        movieId = intent.getIntExtra("Movie_Id", 438148)
    }
}