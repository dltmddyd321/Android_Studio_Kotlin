package com.sycompany.bojstep

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private var settingBroadcastReceiver: LocaleChangedReceiver? = null
    private val localBroadcastManager: LocalBroadcastManager? = null
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var textName : TextView
    private lateinit var textAge : TextView
    private lateinit var nextBtn : Button
    private lateinit var nextBtn2 : Button
    private lateinit var progress : ProgressBar
    private lateinit var seekBar: SeekBar

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        initResultText()
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로가기 클릭 시 실행시킬 코드 입력
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.onBackPressedDispatcher.addCallback(this, callback)

        settingBroadcastReceiver = LocaleChangedReceiver()

        textName = findViewById(R.id.textName)
        textAge = findViewById(R.id.textAge)
        nextBtn = findViewById(R.id.nextBtn)
        nextBtn2 = findViewById(R.id.nextBtnTwo)
        progress = findViewById(R.id.testProgressBar)
        seekBar = findViewById(R.id.seekBar)

        initResultText()

        nextBtn.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            resultLauncher.launch(intent) // startActivityForResult 랑 동일한 기능 이다.
        }

        nextBtn2.setOnClickListener {
            val intent = Intent(this,RxActivity::class.java)
            startActivity(intent)
        }

        textName.setOnClickListener {
            val intent = Intent(this,ImageActivity::class.java)
            startActivity(intent)
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

        })
    }

    @SuppressLint("SetTextI18n")
    private fun initResultText() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val name = it.data?.getStringExtra("name") ?: ""
                val age = it.data?.getStringExtra("age") ?: ""

                textName.text = "이름 : $name"
                textAge.text = "나이 : $age 살"
            }
        }
    }

    //Integer 값을 DP 값으로 변환
    inline val Int.toDp: Int
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()

    override fun onResume() {
        super.onResume()
        val intent = Intent()
        intent.action = Intent.ACTION_TIMEZONE_CHANGED
//        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
        val broadcastManager = LocalBroadcastManager.getInstance(this)
        broadcastManager.sendBroadcast(intent)

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        settingBroadcastReceiver?.let { broadcastManager.registerReceiver(it, intentFilter) }

//        settingBroadcastReceiver?.let { broadcastManager.unregisterReceiver(it) }
    }

    override fun onPause() {
        super.onPause()
    }
}