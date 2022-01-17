package com.example.unsplash

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unsplash.model.Photo
import com.example.unsplash.recyclerview.PhotoGridRecyclerviewAdapter
import kotlinx.android.synthetic.main.activity_photo_collection.*

class PhotoCollectionActivity: AppCompatActivity() {

    private var photoList = ArrayList<Photo>()
    private lateinit var photoGridRecyclerviewAdapter: PhotoGridRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        val bundle = intent.getBundleExtra("arrayBundle")
        val searchTerm = intent.getStringExtra("searchTerm")

        photoList = bundle?.getSerializable("photoArrayList") as ArrayList<Photo>

        top_app_bar.title = searchTerm

        this.photoGridRecyclerviewAdapter = PhotoGridRecyclerviewAdapter()
        this.photoGridRecyclerviewAdapter.submitList(photoList)
        photoRecyclerview.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        photoRecyclerview.adapter = this.photoGridRecyclerviewAdapter
    }
}