package com.sycompany.frescobasic

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.view.SimpleDraweeView

class MainActivity : AppCompatActivity() {

    private lateinit var draweeView: SimpleDraweeView

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        draweeView = findViewById(R.id.draweeView)

        val hierarchy: GenericDraweeHierarchy = GenericDraweeHierarchyBuilder(resources)
            .setFadeDuration(300)
            .setBackground(getDrawable(R.drawable.ic_launcher_background))
            .setPlaceholderImage(getDrawable(R.drawable.ic_launcher_background))
            .setFailureImage(getDrawable(R.drawable.ic_launcher_foreground))
            .build()
        draweeView.hierarchy = hierarchy

        val uri = "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp"
        val controller = Fresco.newDraweeControllerBuilder()
            .setUri(uri)
            .setAutoPlayAnimations(true) // 자동 webp 실행
            .build()
        draweeView.controller = controller
    }
}