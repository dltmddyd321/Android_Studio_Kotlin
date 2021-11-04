package com.example.picapp.repositories

import android.graphics.Bitmap
import android.net.Uri

interface EditImageRepository {
    //일시중단 가능 코루틴
    suspend fun prepareImagePreview(imageUri: Uri) : Bitmap?
}