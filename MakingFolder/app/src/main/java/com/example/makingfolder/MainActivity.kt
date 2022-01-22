package com.example.makingfolder

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSaveFolder()

        Toast.makeText(
            applicationContext,
            "folder name : " + getSaveFolder().absolutePath,
            Toast.LENGTH_SHORT
        ).show()

    }

    private fun getSaveFolder(): File {
        val folderName = "TBstorage"
        val dir = File("/storage/$folderName")
        if (!dir.exists()) {
            dir.mkdirs()
        } else {
            Log.d("isExists?","이미 존재하는 폴더!")
        }
        return dir
    }


//    @SuppressLint("NewApi")
//    fun checkVerify() {
//        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
//            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//        ) {
//            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            }
//            requestPermissions(
//                arrayOf(
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//                ), 1
//            )
//        }
//    }
}