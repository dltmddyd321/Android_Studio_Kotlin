package com.example.soltext2

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import org.chromium.base.Log
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.InputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.text1)

        val superAsset = resources.assets
        val ins = superAsset.open("data.json")

        var str = ins.bufferedReader().use { it.readText() }

        try {
            val pageMap = JSONObject(str).getJSONObject("pageMap")
            val pageNumber = pageMap.getString("pageNumber")

            resultTextView.text = pageNumber
        } catch (e : Exception) {
            e.printStackTrace()
        }

        ins.close()

    }
}