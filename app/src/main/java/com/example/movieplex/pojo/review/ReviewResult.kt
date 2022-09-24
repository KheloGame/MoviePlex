package com.example.movieplex.pojo.review


import com.google.gson.annotations.SerializedName

data class ReviewResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Review>,
    @SerializedName("total_results")
    val totalResults: Int
)