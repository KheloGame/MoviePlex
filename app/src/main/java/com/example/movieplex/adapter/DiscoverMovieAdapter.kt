package com.example.movieplex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieplex.databinding.ItemDiscoverMovieBinding
import com.example.movieplex.pojo.Movie
import com.example.movieplex.util.Constants.URL_IMG

class DiscoverMovieAdapter : RecyclerView.Adapter<DiscoverMovieAdapter.DiscoverViewHolder>(){
    lateinit var onItemClick: ((Movie) -> Unit)
    private var movieList = ArrayList<Movie>()

    fun setMovie(discoverMovieList: ArrayList<Movie>){
        this.movieList = discoverMovieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        return DiscoverViewHolder(ItemDiscoverMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(URL_IMG+movieList[position].posterPath)
            .into(holder.binding.imgDiscover)

        holder.binding.tvTitle.text = movieList[position].title
        holder.binding.tvLanguage.text = movieList[position].originalLanguage
        val rating = movieList[position].voteAverage
        holder.binding.rbRate.rating = (rating/2).toFloat()

        holder.itemView.setOnClickListener {
            onItemClick.invoke(movieList[position])
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class DiscoverViewHolder(val binding: ItemDiscoverMovieBinding): RecyclerView.ViewHolder(binding.root)
}