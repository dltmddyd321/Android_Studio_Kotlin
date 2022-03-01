package com.example.clearappdata

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clearAppData(this)
    }

    fun clearAppData(context: Context) {
        val cache = context.cacheDir
        val appDir = File(cache.parent)

        if(appDir.exists()) {
            val children = appDir.list()
            for (s in children) {
                if(!s.equals("shared_prefs") && !s.equals("files")) {
                    Log.d("삭제 대상", "폴더명 : $s")
                    deleteDir(File(appDir, s))
                }
            }
        }
    }

    fun deleteDir(dir: File): Boolean {
        if (dir.isDirectory) {
            val children = dir.list()

            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }
        return dir.delete()
    }
}