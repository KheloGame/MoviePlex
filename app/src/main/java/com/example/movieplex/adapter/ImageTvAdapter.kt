package com.example.movieplex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieplex.R
import com.example.movieplex.pojo.images.Backdrop
import com.example.movieplex.util.Constants.URL_IMG

class ImageTvAdapter : RecyclerView.Adapter<ImageTvAdapter.ImageTvViewHolder>() {
    private var imageList = ArrayList<Backdrop>()

    fun setImage(imageList: ArrayList<Backdrop>){
        this.imageList = imageList
        notifyDataSetChanged()
    }

//    inner class ImageTvViewHolder(val binding: ItemImagesTvBinding): RecyclerView.ViewHolder(binding.root)

    class ImageTvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.img_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageTvViewHolder {
//        return ImageTvViewHolder(ItemImagesTvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images_tv, parent, false)
        return ImageTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageTvViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(URL_IMG +imageList[position].filePath)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}