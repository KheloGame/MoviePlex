package com.example.movieplex.pojo

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("results")
    val results: List<Movie>
)