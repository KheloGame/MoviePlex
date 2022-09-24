package com.example.movieplex.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieplex.adapter.DefaultTvAdapter
import com.example.movieplex.adapter.ImageTvAdapter
import com.example.movieplex.adapter.ReviewAdapter
import com.example.movieplex.databinding.ActivityTvDetailsBinding
import com.example.movieplex.pojo.Tv
import com.example.movieplex.pojo.TvDetails
import com.example.movieplex.pojo.images.Backdrop
import com.example.movieplex.pojo.review.Review
import com.example.movieplex.util.Constants
import com.example.movieplex.viewmodel.MovieViewModel
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class TvDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvDetailsBinding

    private lateinit var viewModel: MovieViewModel

    private var tvId by Delegates.notNull<Int>()

    private lateinit var reviewTvAdapter: ReviewAdapter
    private lateinit var imageTvAdapter: ImageTvAdapter
    private lateinit var similarTvAdapter: DefaultTvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        imageTvAdapter = ImageTvAdapter()
        reviewTvAdapter = ReviewAdapter()
        similarTvAdapter = DefaultTvAdapter()

        prepareImageTvRecyclerView()
        prepareReviewTvRecyclerView()
        prepareSimilarTvRecyclerView()

        getTvId()

        viewModel.getTvDetails(tvId)
        observeTvDetailsLiveData()

        viewModel.getImagesTv(tvId)
        observeImageTvLiveData()

        viewModel.getReviewTv(tvId)
        observeReviewTvLiveData()
        reviewTvItemClick()

        viewModel.getSimilarTv(tvId)
        observeSimilarTvLiveData()
        similarTvItemClick()

        onFabBackClick()
    }

    private fun onFabBackClick() {
        binding.fabBack.setOnClickListener {
            finish()
        }
    }

    private fun observeImageTvLiveData() {
        viewModel.imagesTvLiveData.observe(this){
            imageTvAdapter.setImage(it as ArrayList<Backdrop>)
        }
    }

    private fun prepareImageTvRecyclerView() {
        binding.rvImages.apply {
            adapter = imageTvAdapter
            setInfinite(true)
            setAlpha(false)
            set3DItem(false)
            setIsScrollingEnabled(true)
            setFlat(true)
        }
    }

    private fun reviewTvItemClick() {
    }

    private fun observeReviewTvLiveData() {
        viewModel.reviewTvLiveData.observe(this){
            reviewTvAdapter.setReviewList(it as ArrayList<Review>)
        }
    }

    private fun prepareReviewTvRecyclerView() {
        binding.rvReview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = reviewTvAdapter
        }
    }

    private fun similarTvItemClick() {
        similarTvAdapter.onItemClick = {
            val intent = Intent(this, TvDetailsActivity::class.java)
            intent.putExtra("Tv_Id", it.id)
            startActivity(intent)
        }
    }

    private fun observeSimilarTvLiveData() {
        viewModel.similarTvLiveData.observe(this){
            similarTvAdapter.setTv(it as ArrayList<Tv>)
        }
    }

    private fun prepareSimilarTvRecyclerView() {
        binding.rvSimilarTv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = similarTvAdapter
        }
    }

    private fun observeTvDetailsLiveData() {
        viewModel.tvDetailsLiveData.observe(this, object : Observer<TvDetails>{
            override fun onChanged(it: TvDetails?) {
                Glide.with(applicationContext)
                    .load(Constants.URL_IMG +it!!.backdropPath)
                    .into(binding.imgBackdrop)

                Glide.with(applicationContext)
                    .load(Constants.URL_IMG +it.posterPath)
                    .into(binding.imgPoster)

                binding.tvTitle.text = it.name
                if(it.tagline.isNotEmpty()){
                    binding.tvTagline.text = it.tagline
                }else{
                    binding.tvTagline.text = "N/A"
                }

                binding.tvReleaseDate.text = SimpleDateFormat("MM/yyyy", Locale.US)
                    .format(SimpleDateFormat("yyyy-MM-dd", Locale.US)
                        .parse(it.firstAirDate) as Date)
                binding.tvSeason.text = "Seasons: ${it.numberOfSeasons}"
                binding.tvEpisode.text = "Episode: ${it.numberOfEpisodes}"
                binding.tvOverview.text = it.overview
                it.genres.forEach {
                    addChip(it.name)
                }
                binding.tvAvgRating.text = "Rating: ${it.voteAverage.toInt()}"
            }

        })
    }

    fun addChip(text: String){
        val chip = Chip(this)
        chip.text = text
        binding.cgGenre.addView(chip)
    }

    private fun getTvId() {
        val intent = intent
        tvId = intent.getIntExtra("Tv_Id", 92783)
    }
}