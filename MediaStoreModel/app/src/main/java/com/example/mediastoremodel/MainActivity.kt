package com.example.mediastoremodel

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets


class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            try {
                val contentValues = ContentValues()

                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "menuCategory")
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/Test/")

                val uri : Uri? = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
                val outputStream : OutputStream? = contentResolver.openOutputStream(uri!!)
                outputStream?.write("This is menu category data.".toByteArray())
                outputStream?.close()

                Toast.makeText(this, "File created successfully", Toast.LENGTH_SHORT).show()
            } catch (e : IOException) {
                Toast.makeText(this, "Fail to create file", Toast.LENGTH_SHORT).show()
            }
        }

        btn2.setOnClickListener {
            val contentUri : Uri = MediaStore.Files.getContentUri("external")
            val selection = MediaStore.MediaColumns.RELATIVE_PATH +">=?"
            val selectionArgs = arrayOf(Environment.DIRECTORY_DOCUMENTS + "/Test/")
            val cursor: Cursor? = contentResolver.query(contentUri, null, selection, selectionArgs, null)
            var uri : Uri? = null

            if(cursor?.count == 0) {
                Toast.makeText(this, "No file found", Toast.LENGTH_LONG).show()
            } else {
                while (cursor!!.moveToNext()) {
                    val fileName = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME))

                    if(fileName.equals("menuCategory.txt")) {
                        val id = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
                        uri = ContentUris.withAppendedId(contentUri, id)
                        break
                    }
                }

                if(uri == null) {
                    Toast.makeText(this, "\"menuCategory.txt\" not found", Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        val inputStream : InputStream = contentResolver.openInputStream(uri)!!
                        val size = inputStream.available()
                        val bytes = ByteArray(size)

                        inputStream.read(bytes)
                        inputStream.close()

                        val jsonString = String(bytes, StandardCharsets.UTF_8)

                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("File Content")
                        builder.setMessage(jsonString)
                        builder.setPositiveButton("OK", null)
                        builder.create().show()
                    } catch (e : IOException) {
                        Toast.makeText(this, "Fail to read file", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btn3.setOnClickListener {
            val contentUri : Uri = MediaStore.Files.getContentUri("external")
            val selection = MediaStore.MediaColumns.RELATIVE_PATH + ">=?"
            val selectionArgs = arrayOf(Environment.DIRECTORY_DOCUMENTS + "/Test/")
            val cursor : Cursor =
                contentResolver.query(contentUri, null, selection, selectionArgs, null)!!
            var uri : Uri? = null

            if(cursor.count == 0) {
                Toast.makeText(this, "No file found", Toast.LENGTH_LONG).show()
            } else {
                while (cursor.moveToNext()) {
                    val fileName = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME))

                    if(fileName.equals("menuCategory.txt")) {
                        val id = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
                        uri = ContentUris.withAppendedId(contentUri, id)
                        break
                    }
                }

                if(uri == null) {
                    Toast.makeText(this, "\"menuCategory.txt\" not found", Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        val outputStream : OutputStream? = contentResolver.openOutputStream(uri!!, "rwt")
                        outputStream?.write("덮어씌워진 데이터입니다.".toByteArray())
                        outputStream?.close()
                        Toast.makeText(this, "File created successfully", Toast.LENGTH_SHORT).show()
                    } catch (e : IOException) {
                        Toast.makeText(this, "Fail to read file", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}