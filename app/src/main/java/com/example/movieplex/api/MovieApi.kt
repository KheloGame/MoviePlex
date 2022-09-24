package com.example.movieplex.api

import com.example.movieplex.pojo.MovieDetails
import com.example.movieplex.pojo.MovieResult
import com.example.movieplex.pojo.TvDetails
import com.example.movieplex.pojo.TvResult
import com.example.movieplex.pojo.images.ImageResult
import com.example.movieplex.pojo.review.ReviewResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("tv/{tv_id}/images")
    fun getImagesTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Call<ImageResult>

    @GET("tv/{tv_id}/reviews")
    fun getReviewsTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Call<ReviewResult>

    @GET("tv/{tv_id}/similar")
    fun getSimilarTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Call<TvResult>

    @GET("tv/top_rated")
    fun getTopRatedTv(
        @Query("api_key") apiKey: String
    ): Call<TvResult>

    @GET("tv/{tv_id}")
    fun getTvDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Call<TvDetails>

    @GET("tv/popular")
    fun getPopularTv(
        @Query("api_key") apiKey: String
    ): Call<TvResult>

    @GET("discover/movie")
    fun getDiscover(
        @Query("api_key") apiKey: String
    ): Call<MovieResult>

    @GET("movie/upcoming")
    fun getUpComing(
        @Query("api_key") apiKey: String
    ): Call<MovieResult>

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String
    ): Call<MovieResult>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetails>

    @GET("search/movie")
    fun getSearchedMovie(
        @Query("api_key") apiKey: String,
        @Query("query") searchQuery: String,
        @Query("include_adult") adult: Boolean
    ): Call<MovieResult>

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendation(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieResult>

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") apiKey: String
    ): Call<MovieResult>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") apiKey: String
    ): Call<MovieResult>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieResult>

    @GET("movie/{movie_id}/images")
    fun getImagesMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<ImageResult>

    @GET("movie/{movie_id}/reviews")
    fun getReviewsMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<ReviewResult>


}