package com.example.coilbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coilView.load("https://lh3.google.com/u/0/ogw/ADea4I5wJdxlWvxfGBydkAuBUieDjpPfwUrC6CMkOKnL=s64-c-mo") {
            crossfade(true)
//            placeholder(R.drawable.image)
//            transformations(CircleCropTransformation())

//            - BlurTransformation
//            이미지를 흐릿하게 보이기하는 가우시안 블러(Gaussian Blur)를 적용합니다.

//            - CircleCropTransformation
//            이미지의 중심을 기준으로 원형으로 이미지를 자릅니다. (예. 카카톡의 원형 프로필 이미지)

//            - GrayscaleTransformation
//            그레이스케일로 음영처리를 적용합니다.

//            - RoundedCornersTransformation
//            사이즈에 맞도록 이미지를 자르고 이미지 모서리를 둥글게 라운드를 적용합니다.
        }
    }
}