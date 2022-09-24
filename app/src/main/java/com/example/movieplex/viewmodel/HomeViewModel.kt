package com.example.movieplex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplex.api.RetrofitInstance
import com.example.movieplex.pojo.Movie
import com.example.movieplex.pojo.MovieResult
import com.example.movieplex.pojo.Tv
import com.example.movieplex.pojo.TvResult
import com.example.movieplex.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private var _discoverListLiveData = MutableLiveData<List<Movie>>()
    val discoverListLiveData: LiveData<List<Movie>> get() = _discoverListLiveData

    private var _upComingListLiveData = MutableLiveData<List<Movie>>()
    val upComingListLiveData: LiveData<List<Movie>> get() = _upComingListLiveData

    private var _nowPlayingListLiveData = MutableLiveData<List<Movie>>()
    val nowPlayingListLivedata: LiveData<List<Movie>> get() = _nowPlayingListLiveData

    private var _searchListLiveData = MutableLiveData<List<Movie>>()
    val searchListLiveData: LiveData<List<Movie>> get() = _searchListLiveData

    private var _recommendationListLiveData = MutableLiveData<List<Movie>>()
    val recommendationListLiveData: LiveData<List<Movie>> get() = _recommendationListLiveData

    private var _popularListLiveData = MutableLiveData<List<Movie>>()
    val popularListLiveData: LiveData<List<Movie>> get() = _popularListLiveData

    private var _topRatedListLiveData = MutableLiveData<List<Movie>>()
    val topRatedListLiveData: LiveData<List<Movie>> get() = _topRatedListLiveData

    private var _popularTvListLiveData = MutableLiveData<List<Tv>>()
    val popularTvListLiveData: LiveData<List<Tv>> get() = _popularTvListLiveData

    private var _topRatedTvListLiveData = MutableLiveData<List<Tv>>()
    val topRatedTvListLiveData: LiveData<List<Tv>> get() = _topRatedTvListLiveData

    fun getTopRatedTv(){
        RetrofitInstance.api.getTopRatedTv(API_KEY).enqueue(object : Callback<TvResult>{
            override fun onResponse(call: Call<TvResult>, response: Response<TvResult>) {
                if (response.body()!=null){
                    _topRatedTvListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<TvResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun getPopularTv(){
        RetrofitInstance.api.getPopularTv(API_KEY).enqueue(object : Callback<TvResult>{
            override fun onResponse(call: Call<TvResult>, response: Response<TvResult>) {
                if (response.body() != null){
                    _popularTvListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<TvResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun getTopRated(){
        RetrofitInstance.api.getTopRated(API_KEY).enqueue(object : Callback<MovieResult>{
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if(response.body()!=null){
                    _topRatedListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())

            }
        })
    }

    fun getPopular(){
        RetrofitInstance.api.getPopular(API_KEY).enqueue(object : Callback<MovieResult>{
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if (response.body()!=null){
                    _popularListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun getRecommendation(movieId: Int){
        RetrofitInstance.api.getRecommendation(movieId, API_KEY).enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if(response.body()!=null){
                    _recommendationListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun getSearchedMovie(searchQuery: String){
        RetrofitInstance.api.getSearchedMovie(API_KEY, searchQuery, false).enqueue(object : Callback<MovieResult>{
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if(response.body()!=null){
                    _searchListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun getDiscover(){
        RetrofitInstance.api.getDiscover(API_KEY).enqueue(object : Callback<MovieResult> {
            override fun onResponse(
                call: Call<MovieResult>,
                response: Response<MovieResult>
            ) {
                if (response.body() != null){
                    _discoverListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun getUpComing(){
        RetrofitInstance.api.getUpComing(API_KEY).enqueue(object : Callback<MovieResult>{
            override fun onResponse(
                call: Call<MovieResult>,
                response: Response<MovieResult>
            ) {
                if (response.body() != null){
                    _upComingListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun getNowPlaying(){
        RetrofitInstance.api.getNowPlaying(API_KEY).enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if (response.body() != null){
                    _nowPlayingListLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }


}