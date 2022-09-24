package com.example.movieplex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieplex.R
import com.example.movieplex.pojo.Tv
import com.example.movieplex.util.Constants.URL_IMG

class PopularTvAdapter : RecyclerView.Adapter<PopularTvAdapter.PopularTvViewHolder>() {
    private var tvList = ArrayList<Tv>()
    lateinit var onItemClickTv: ((Tv) -> Unit)

    class PopularTvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nowPlayingImg: ImageView = itemView.findViewById(R.id.img_now_playing)
    }

    fun setTv(popularTvList: ArrayList<Tv>){
        this.tvList = popularTvList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing_movie, parent, false)
        return PopularTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularTvViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(URL_IMG+tvList[position].posterPath)
            .into(holder.nowPlayingImg)

        holder.itemView.setOnClickListener {
            onItemClickTv.invoke(tvList[position])
        }
    }

    override fun getItemCount(): Int {
        return tvList.size
    }

}