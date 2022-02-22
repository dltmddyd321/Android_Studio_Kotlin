package com.example.videoview

import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val VIDEO_URL = "https://ia800309.us.archive.org/8/items/Tacoma-Narrows_Bridge_Collapse/2096_Tacoma-Narrows_Bridge_Collapse_03_11_13_15_3mb.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var uri : Uri = Uri.parse(VIDEO_URL)
        videoView.setVideoURI(uri)
        videoView.setMediaController(MediaController(this))
        videoView.requestFocus()

        videoView.setOnPreparedListener {
            Toast.makeText(applicationContext, "동영상 재생 준비 완료", Toast.LENGTH_SHORT).show()
            videoView.start()
        }

        videoView.setOnCompletionListener {
            Toast.makeText(applicationContext, "동영상 시청 완료", Toast.LENGTH_SHORT).show()
        }

        btnStart.setOnClickListener {
            videoView.start()
        }

        btnResume.setOnClickListener {
            videoView.resume()
        }

        btnPause.setOnClickListener {
            videoView.pause()
        }

        btnStop.setOnClickListener {
            videoView.pause()
            videoView.stopPlayback()
        }
    }
}