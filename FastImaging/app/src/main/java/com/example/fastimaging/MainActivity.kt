package com.example.fastimaging

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun storeInLocalStorage(imageView: ImageView, url: String) {

        GlobalScope.launch {
            try {
                var bitmap: Bitmap = try {
                    val bitmapDrawable = imageView.drawable as RoundedBitmapDrawable
                    bitmapDrawable.sourceBitmap
                } catch (e: java.lang.Exception) {
                    val bitmapDrawable = imageView.drawable as BitmapDrawable
                    bitmapDrawable.bitmap
                }
                val finalBitmap = bitmap
                val file = getFileNameFromUrl(imageView.context, url)
                if (!file?.exists()) file.createNewFile()
                val out = FileOutputStream(file)
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)
                out.flush()
                out.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                Log.i(TAG, "storeInLocalStorage: " + e.localizedMessage)
            }
        }

    }

    fun getFileNameFromUrl(context: Context, url: String) : File? {
        var file : File? = null
        file = try {
            val root = ContextWrapper(context).getDir("Image", Context.MODE_PRIVATE).absolutePath
            val myDir = File("$root/cache")
            myDir.mkdirs()
            val part = url.split("/").toTypedArray()
            val part2 = part[part.size - 1].split("v=").toTypedArray()
            val imageName = part2[0].replace("?","")
            File(myDir, imageName)
        } catch (e : Exception) {
            return null
        }
        return file
    }
}