package com.example.movieplex.pojo


import com.google.gson.annotations.SerializedName

data class TvResult(
    @SerializedName("results")
    val results: List<Tv>,
)