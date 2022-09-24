package com.example.movieplex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieplex.databinding.ItemSearchMovieBinding
import com.example.movieplex.pojo.Movie
import com.example.movieplex.util.Constants.URL_IMG

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.SearchViewHolder>() {
    lateinit var onItemClick: ((Movie) -> Unit)
    private var movieList = ArrayList<Movie>()

    fun setMovie(searchMovieList: ArrayList<Movie>){
        this.movieList = searchMovieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemSearchMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(URL_IMG+movieList[position].posterPath)
            .into(holder.binding.imgPoster)

        holder.binding.tvTitle.text = movieList[position].title
        holder.binding.tvReleaseDate.text = movieList[position].releaseDate
        holder.binding.tvOverview.text = movieList[position].overview
        val rating = movieList[position].voteAverage
        holder.binding.rbRate.rating = (rating/2).toFloat()

        holder.itemView.setOnClickListener {
            onItemClick.invoke(movieList[position])
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class SearchViewHolder(val binding: ItemSearchMovieBinding): RecyclerView.ViewHolder(binding.root)
}