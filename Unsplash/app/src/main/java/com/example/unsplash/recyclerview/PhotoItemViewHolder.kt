package com.example.unsplash.recyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.example.unsplash.App
import com.example.unsplash.R
import com.example.unsplash.model.Photo
import com.example.unsplash.utils.Constants
import kotlinx.android.synthetic.main.layout_photo_item.view.*

class PhotoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val photoImageView = itemView.photoImg
    private val photoCreatedText = itemView.createdText
    private val photoLikesCntText = itemView.likeCntText

    fun bindWithView(photoItem: Photo) {
        Log.d(Constants.TAG, "PhotoItemViewHolder - bindWithView() called")

        photoCreatedText.text = photoItem.createdAt
        photoLikesCntText.text = photoItem.likesCount.toString()

        Glide.with(App.instance)
            .load(photoItem.thumbnail)
            .placeholder(R.drawable.ic_baseline_insert_photo_24)
            .into(photoImageView)
    }

}