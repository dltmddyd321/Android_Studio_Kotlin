package com.example.assettextfile

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var currentPhotoPath: String
    lateinit var assetManager: AssetManager
    lateinit var inputStream: InputStream
    lateinit var bitmap: Bitmap
    private val REQUEST_TAKE_PHOTO = 1

    private val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val extras = it.data?.extras
            bitmap = extras?.get("data") as? Bitmap ?: return@registerForActivityResult
            Glide.with(this)
                .load(bitmap)
                .into(photoImg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        assetManager = resources.assets

        btn.setOnClickListener {
            getData()
        }

        btnPhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            activityResult.launch(intent)
        }
    }

    private fun createImgFile(): File {
        // Create an image file name
        val nowTime = System.currentTimeMillis()
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${nowTime}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePicIntent ->
            takePicIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImgFile()
                } catch (e: IOException) {
                    null
                }
                photoFile?.also {
                    val photoUri = FileProvider.getUriForFile(
                        this, "com.example.android.fileprovider", it
                    )
                    takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    startActivityForResult(takePicIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        try {
            val inputStream : InputStream = assetManager.open("lastinfo.json")
            val inputStreamReader = InputStreamReader(inputStream)
            val reader = BufferedReader(inputStreamReader)

            val buffer = StringBuffer()
            var line = reader.readLine()
            while (line!=null) {
               buffer.append(line+"\n")
               line = reader.readLine()
            }

            val data = buffer.toString()

            val jsonObject = JSONObject(data)
            val id = jsonObject.getString("id")
            val language = jsonObject.getString("language")

            txtData.text = "아이디 : $id / 언어 : $language"

        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    private fun getTextData() {
        try {
            inputStream = assetManager.open("text.txt", AssetManager.ACCESS_BUFFER)
            val reader : BufferedReader = BufferedReader(InputStreamReader(inputStream))

            val strResult = ""
            val line = ""

        } catch (e : IOException) {
            e.printStackTrace()
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close()
                } catch (e : IOException) {}
            }
        }
    }

    private fun getJsonData() {
        val jsonString = assets.open("lastinfo.json").reader().readText()
        Log.d("jsonArray", jsonString)

        val jsonArray = JSONArray(jsonString)
        Log.d("jsonArray", jsonArray.toString())

        for(index in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(index)

            val id = jsonObject.getString("id")
            val language = jsonObject.getString("language")

//            textView.text = "$id $language"
        }
    }
}