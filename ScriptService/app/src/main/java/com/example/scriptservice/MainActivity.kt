package com.example.scriptservice

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.googlecode.tesseract.android.TessBaseAPI
import java.io.*

class MainActivity : AppCompatActivity() {

    private lateinit var tess: TessBaseAPI
    private lateinit var textView: TextView
    var dataPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.mainText)

        dataPath = "$filesDir/tesseract/" //언어데이터의 경로 미리 지정

        checkFile(File(dataPath + "tessdata/"), "kor") //사용할 언어파일의 이름 지정
        checkFile(File(dataPath + "tessdata/"), "eng")

        val lang = "kor+eng"
        tess = TessBaseAPI() //api준비
        tess.init(dataPath, lang) //해당 사용할 언어 데이터로 초기화

        processImage(BitmapFactory.decodeResource(resources, R.drawable.textexample))
    }

    private fun copyFile(lang: String) {
        try {
            //언어 파일의 위치
            val filePath: String = "$dataPath/tessdata/$lang.traineddata"

            //AssetManager를 사용하기 위한 객체 생성
            val assetManager: AssetManager = assets

            //byte 스트림을 읽기 쓰기용으로 열기
            val inputStream: InputStream = assetManager.open("testdata/$lang.traineddata")
            val outStream: OutputStream = FileOutputStream(filePath)

            //위에 적어둔 파일 경로쪽으로 해당 바이트코드 파일을 복사한다.
            val buffer: ByteArray = ByteArray(1024)

            var read = inputStream.read(buffer)
            while (read != -1) {
                outStream.write(buffer, 0, read)
                read = inputStream.read(buffer)
            }
            outStream.flush()
            outStream.close()
            inputStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun checkFile(dir: File, lang: String) {
        if (!dir.exists() && dir.mkdirs()) {
            copyFile(lang)
        }

        if (dir.exists()) {
            val datafilePath = "$dataPath/tessdata/$lang.traineddata"
            val dataFile = File(datafilePath)
            if (!dataFile.exists()) {
                copyFile(lang)
            }
        }
    }

    private fun processImage(bitmap: Bitmap) {
        Toast.makeText(applicationContext, "잠시만 기다려 주세요", Toast.LENGTH_SHORT).show()
        tess.setImage(bitmap)
        val ocrResult = tess.utF8Text
        textView.text = ocrResult
    }
}