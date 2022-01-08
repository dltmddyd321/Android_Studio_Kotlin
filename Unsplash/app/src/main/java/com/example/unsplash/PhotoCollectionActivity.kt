package com.example.unsplash

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unsplash.model.Photo

class PhotoCollectionActivity: AppCompatActivity() {

    var photoList = ArrayList<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        val bundle = intent.getBundleExtra("arrayBundle")
        val searchTerm = intent.getStringExtra("searchTerm")

        photoList = bundle?.getSerializable("photoArrayList") as ArrayList<Photo>
    }
}