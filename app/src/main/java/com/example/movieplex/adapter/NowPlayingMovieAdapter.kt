package com.example.movieplex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieplex.R
import com.example.movieplex.pojo.Movie
import com.example.movieplex.util.Constants.URL_IMG

class NowPlayingMovieAdapter : RecyclerView.Adapter<NowPlayingMovieAdapter.NowPlayingMovieViewHolder>() {
    private var movieList = ArrayList<Movie>()
    lateinit var onItemClick: ((Movie) -> Unit)

    fun setMovie(nowPlayingList: ArrayList<Movie>){
        this.movieList = nowPlayingList
        notifyDataSetChanged()
    }

    class NowPlayingMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nowPlayingImg: ImageView = itemView.findViewById(R.id.img_now_playing)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing_movie, parent, false)
        return NowPlayingMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingMovieViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(URL_IMG+movieList[position].posterPath)
            .into(holder.nowPlayingImg)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(movieList[position])
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}