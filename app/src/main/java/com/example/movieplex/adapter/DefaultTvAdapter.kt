package com.example.movieplex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieplex.databinding.ItemDiscoverMovieBinding
import com.example.movieplex.pojo.Tv
import com.example.movieplex.util.Constants.URL_IMG

class DefaultTvAdapter : RecyclerView.Adapter<DefaultTvAdapter.DefaultViewHolder>(){
    lateinit var onItemClick: ((Tv) -> Unit)
    private var tvList = ArrayList<Tv>()

    fun setTv(defaultTvList: ArrayList<Tv>){
        this.tvList = defaultTvList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        return DefaultViewHolder(ItemDiscoverMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(URL_IMG+tvList[position].posterPath)
            .into(holder.binding.imgDiscover)

        holder.binding.tvTitle.text = tvList[position].name
        holder.binding.tvLanguage.text = tvList[position].originalLanguage
        val rating = tvList[position].voteAverage
        holder.binding.rbRate.rating = (rating/2).toFloat()

        holder.itemView.setOnClickListener {
            onItemClick.invoke(tvList[position])
        }
    }

    override fun getItemCount(): Int {
        return tvList.size
    }

    class DefaultViewHolder(val binding: ItemDiscoverMovieBinding): RecyclerView.ViewHolder(binding.root)
}