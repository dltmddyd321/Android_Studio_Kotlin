package com.example.alarmmanager

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TimePicker
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var regBtn : Button
    lateinit var delBtn : Button
    lateinit var timePicker : TimePicker
    lateinit var alarmManager: AlarmManager
    var hour = 0
    var minute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker = findViewById(R.id.tp_timepicker)
        regBtn = findViewById(R.id.button)
        delBtn = findViewById(R.id.button2)

        regBtn.setOnClickListener {
            registerAlarm()
        }

        delBtn.setOnClickListener {
            unRegisterAlarm()
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun registerAlarm() {
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent : PendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.hour
            minute = timePicker.minute
        }

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        //반복 알람 설정
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun unRegisterAlarm() {
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent : PendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        //알람 해제
        alarmManager.cancel(pendingIntent)
    }
}