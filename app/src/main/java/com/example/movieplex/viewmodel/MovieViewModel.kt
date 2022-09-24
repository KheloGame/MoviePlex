package com.example.movieplex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplex.api.RetrofitInstance
import com.example.movieplex.pojo.*
import com.example.movieplex.pojo.images.Backdrop
import com.example.movieplex.pojo.images.ImageResult
import com.example.movieplex.pojo.review.Review
import com.example.movieplex.pojo.review.ReviewResult
import com.example.movieplex.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {

    private var _movieDetailsLiveData = MutableLiveData<MovieDetails>()
    val movieDetailsLiveData: LiveData<MovieDetails> get() = _movieDetailsLiveData

    private var _tvDetailsLiveData = MutableLiveData<TvDetails>()
    val tvDetailsLiveData: LiveData<TvDetails> get() = _tvDetailsLiveData

    private var _similarTvLiveData = MutableLiveData<List<Tv>>()
    val similarTvLiveData: LiveData<List<Tv>> get() = _similarTvLiveData

    private var _similarMovieListLiveData = MutableLiveData<List<Movie>>()
    val similarMovieListLiveData: LiveData<List<Movie>> get() = _similarMovieListLiveData

    private var _reviewTvLiveData = MutableLiveData<List<Review>>()
    val reviewTvLiveData: LiveData<List<Review>> get() = _reviewTvLiveData

    private var _imagesTvLiveData = MutableLiveData<List<Backdrop>>()
    val imagesTvLiveData: LiveData<List<Backdrop>> get() = _imagesTvLiveData

    private var _imagesMovieLiveData = MutableLiveData<List<Backdrop>>()
    val imagesMovieLiveData: LiveData<List<Backdrop>> get() = _imagesMovieLiveData

    private var _reviewMovieLiveData = MutableLiveData<List<Review>>()
    val reviewMovieLiveData: LiveData<List<Review>> get() = _reviewMovieLiveData

    fun getReviewMovie(movieId: Int){
        RetrofitInstance.api.getReviewsMovie(movieId, API_KEY).enqueue(object : Callback<ReviewResult>{
            override fun onResponse(call: Call<ReviewResult>, response: Response<ReviewResult>) {
                if (response.body()!=null){
                    _reviewMovieLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<ReviewResult>, t: Throwable) {
                Log.d("MovieViewModel", t.message.toString())
            }
        })
    }


    fun getImagesMovie(movieId: Int){
        RetrofitInstance.api.getImagesMovie(movieId, API_KEY).enqueue(object : Callback<ImageResult>{
            override fun onResponse(call: Call<ImageResult>, response: Response<ImageResult>) {
                if (response.body()!=null){
                    _imagesMovieLiveData.value = response.body()!!.backdrops
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<ImageResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun getImagesTv(tvId: Int){
        RetrofitInstance.api.getImagesTv(tvId, API_KEY).enqueue(object : Callback<ImageResult>{
            override fun onResponse(call: Call<ImageResult>, response: Response<ImageResult>) {
                if(response.body()!=null){
                    _imagesTvLiveData.value = response.body()!!.backdrops
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<ImageResult>, t: Throwable) {
                Log.d("MovieViewModel", t.message.toString())
            }
        })
    }

    fun getReviewTv(tvId: Int){
        RetrofitInstance.api.getReviewsTv(tvId, API_KEY).enqueue(object : Callback<ReviewResult>{
            override fun onResponse(call: Call<ReviewResult>, response: Response<ReviewResult>) {
                if (response.body()!=null){
                    _reviewTvLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<ReviewResult>, t: Throwable) {
                Log.d("MovieViewModel", t.message.toString())
            }
        })
    }

    fun getSimilarMovie(movieId: Int){
        RetrofitInstance.api.getSimilarMovie(movieId, API_KEY).enqueue(object : Callback<MovieResult>{
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if (response.body() != null){
                    _similarMovieListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.d("MovieViewModel", t.message.toString())
            }
        })
    }

    fun getSimilarTv(tvId: Int){
        RetrofitInstance.api.getSimilarTv(tvId, API_KEY).enqueue(object : Callback<TvResult>{
            override fun onResponse(call: Call<TvResult>, response: Response<TvResult>) {
                if (response.body() != null){
                    _similarTvLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<TvResult>, t: Throwable) {
                Log.d("MovieViewModel", t.message.toString())
            }
        })
    }

    fun getTvDetails(tvId: Int){
        RetrofitInstance.api.getTvDetails(tvId, API_KEY).enqueue(object : Callback<TvDetails>{
            override fun onResponse(call: Call<TvDetails>, response: Response<TvDetails>) {
                if (response.body() != null){
                    _tvDetailsLiveData.value = response.body()
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<TvDetails>, t: Throwable) {
                Log.d("MovieViewModel", t.message.toString())
            }
        })
    }

    fun getMovieDetails(movieId: Int){
        RetrofitInstance.api.getMovieDetails(movieId, API_KEY).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                if (response.body() != null){
                    _movieDetailsLiveData.value = response.body()
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.d("MovieViewModel", t.message.toString())
            }

        })
    }

}