package com.example.picapp.activities.editimage

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.picapp.activities.main.MainActivity
import com.example.picapp.databinding.ActivityEditImageBinding

class EditImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        displayImagePreview()
    }

    private fun displayImagePreview() {
        //객체 자체를 Intent 전달
        intent.getParcelableExtra<Uri>(MainActivity.KEY_IMAGE_URI)?.let { imageUri ->
            //App 외부 저장소 데이터를 가져오기 위한 contentResolver
            val inputStream = contentResolver.openInputStream(imageUri)
            
            //가져온 이미지 데이터를 저장할 변수
            val bitmap = BitmapFactory.decodeStream(inputStream)

            binding.imgPreview.setImageBitmap(bitmap)
            binding.imgPreview.visibility = View.VISIBLE
        }
    }

    private fun setListeners() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}