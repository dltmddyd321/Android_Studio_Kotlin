package com.example.unsplash.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.R
import com.example.unsplash.model.Photo

//Custom ViewHolder
class PhotoGridRecyclerviewAdapter : RecyclerView.Adapter<PhotoItemViewHolder>() {

    private var photoList = ArrayList<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        return PhotoItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_photo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bindWithView(this.photoList[position])
    }

    override fun getItemCount(): Int = this.photoList.size

    fun submitList(photoList: ArrayList<Photo>) {
        this.photoList = photoList
    }
}