package com.sycompany.frescobasic

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.view.SimpleDraweeView

class MainActivity : AppCompatActivity() {

    private var selectedUri: Uri? = null
    private lateinit var draweeView: SimpleDraweeView
    private lateinit var selectBtn: Button
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) return@registerForActivityResult
            showFilePicker()
        }
    private var filePickerResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult
            selectedUri = result.data?.data ?: return@registerForActivityResult
            showWebpMotion(selectedUri)
        }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        draweeView = findViewById(R.id.draweeView)
        selectBtn = findViewById(R.id.getBtn)

        val hierarchy: GenericDraweeHierarchy = GenericDraweeHierarchyBuilder(resources)
            .setFadeDuration(300)
            .setFailureImage(getDrawable(R.drawable.ic_launcher_foreground))
            .build()
        draweeView.hierarchy = hierarchy

        selectBtn.setOnClickListener {
            checkExternalStoragePermission()
        }
//        val uri = "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp"
//        val controller = Fresco.newDraweeControllerBuilder()
//            .setUri(uri)
//            .setAutoPlayAnimations(true) // 자동 webp 실행
//            .build()
//        draweeView.controller = controller
    }

    private fun showWebpMotion(uri: Uri?) {
        val controller = Fresco.newDraweeControllerBuilder()
            .setUri(uri)
            .setAutoPlayAnimations(true) // 자동 webp 실행
            .build()
        draweeView.controller = controller
    }

    private fun showFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            filePickerResultLauncher.launch(Intent.createChooser(intent, "Select a File to Upload"))
        } catch (_: android.content.ActivityNotFoundException) {
        }
    }

    private fun checkExternalStoragePermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            showFilePicker()
        }
    }
}