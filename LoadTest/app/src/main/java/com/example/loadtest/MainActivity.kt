package com.example.loadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filePath = filesDir.path + "/myText.txt"
        Log.d("Test", "path : $filePath")

        writeTextToFile(filePath)
        readTextFromFile(filePath)
    }

    fun writeTextToFile(path: String) {
        val file = File(path)
        val fileWriter = FileWriter(file, false)
        val bufferedWriter = BufferedWriter(fileWriter)

        bufferedWriter.append("Test1\n")
        bufferedWriter.append("Test2")
        bufferedWriter.newLine()
        bufferedWriter.append("Test3\n")
        bufferedWriter.close()
    }

    fun readTextFromFile(path: String) {
        val file = File(path)
        val fileReader = FileReader(file)
        val bufferedReader = BufferedReader(fileReader)

        bufferedReader.readLines().forEach() {
            Log.d("Test", it)
        }
    }
}