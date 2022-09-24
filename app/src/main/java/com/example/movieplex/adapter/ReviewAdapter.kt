package com.example.movieplex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplex.databinding.ItemReviewBinding
import com.example.movieplex.pojo.Movie
import com.example.movieplex.pojo.review.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    private var reviewList = ArrayList<Review>()

    fun setReviewList(reviewList: ArrayList<Review>){
        this.reviewList = reviewList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.binding.tvUsername.text = reviewList[position].author
        holder.binding.tvContent.text = reviewList[position].content

    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    inner class ReviewViewHolder(val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root)
}