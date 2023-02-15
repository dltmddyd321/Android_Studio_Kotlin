package com.example.assettextfile

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_photo.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class PhotoActivity : AppCompatActivity() {

    val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    val PERMISSIONS_REQUEST = 100

    // Request Code
    private val BUTTON1 = 100
    private val BUTTON2 = 200
    private val BUTTON3 = 300
    private val BUTTON4 = 400
    private val BUTTON5 = 500

    // 원본 사진이 저장되는 Uri
    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        checkPermissions(PERMISSIONS)

        btn1.setOnClickListener {
            val takePicIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePicIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePicIntent, BUTTON1)
            }
        }

        btn2.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, BUTTON2)
            }
        }

        btn3.setOnClickListener {
            val takePicIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val photoFile = File(
                File("${filesDir}/image").apply{
                    if(!this.exists()){
                        this.mkdirs()
                    }
                },
                newJpgFileName()
            )
            photoUri = FileProvider.getUriForFile(
                this,
                "com.example.assettextfile.fileprovider",
                photoFile
            )
            takePicIntent.resolveActivity(packageManager)?.also{
                takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePicIntent, BUTTON3)
            }
        }

        btn4.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val photoFile = File(
                File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image").apply{
                    if(!this.exists()){
                        this.mkdirs()
                    }
                },
                newJpgFileName()
            )
            photoUri = FileProvider.getUriForFile(
                this,
                "com.example.assettextfile.fileprovider",
                photoFile
            )
            takePictureIntent.resolveActivity(packageManager)?.also{
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePictureIntent, BUTTON4)
            }
        }

        btn5.setOnClickListener {
            val values = ContentValues()
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, newJpgFileName())
            values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/TakePicture")

            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            takePictureIntent.resolveActivity(packageManager)?.also{
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePictureIntent, BUTTON5)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                BUTTON1 -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imgView.setImageBitmap(imageBitmap)
                }
                BUTTON2 -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    saveBitmapAsJPGFile(imageBitmap)
                    imgView.setImageBitmap(imageBitmap)
                }
                BUTTON3, BUTTON4, BUTTON5 -> {
                    val imageBitmap = photoUri?.let { ImageDecoder.createSource(this.contentResolver, it) }
                    imgView.setImageBitmap(imageBitmap?.let {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            ImageDecoder.decodeBitmap(it)
                        } else {
                            MediaStore.Images.Media.getBitmap(contentResolver, photoUri)
                        }
                    }
                    )
                    Toast.makeText(this, photoUri?.path, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun newJpgFileName(): String = "${System.currentTimeMillis()}.jpg"

    private fun saveBitmapAsJPGFile(imageBitmap: Bitmap) {
        val path = File(filesDir, "image")
        if (!path.exists()) {
            path.mkdirs()
        }
        val file = File(path, newJpgFileName())
        var imageFile: OutputStream? = null
        try {
            file.createNewFile()
            imageFile = FileOutputStream(file)
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageFile)
            imageFile.close()
            Toast.makeText(this, file.absolutePath, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            null
        }
    }

    private fun checkPermissions(permissions: Array<String>): Boolean {
        val permissionList: MutableList<String> = mutableListOf()
        for (permission in permissions) {
            val result = ContextCompat.checkSelfPermission(this, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission)
            }
        }
        if (permissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionList.toTypedArray(),
                PERMISSIONS_REQUEST
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한 승인 부탁드립니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}