package com.example.timezone

import android.content.*
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val br : BroadcastReceiver = ScreenBrodcastReceiver()
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
        }

        val intentFilter = IntentFilter(Intent.ACTION_TIMEZONE_CHANGED)

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                Toast.makeText(this@MainActivity, "Open!!", Toast.LENGTH_SHORT).show()
//                moveTaskToBack(true)
//                finishAndRemoveTask()
//                android.os.Process.killProcess(android.os.Process.myPid())
//                val intent = Intent(this@MainActivity, MainActivity::class.java)
//                startActivity(intent)

                val packageManager : PackageManager = packageManager
                val intent : Intent? = packageManager.getLaunchIntentForPackage(packageName)
                val componentName : ComponentName? = intent?.component
                val mainIntent : Intent = Intent.makeRestartActivityTask(componentName)
                startActivity(mainIntent)
                exitProcess(0)
            }
        }
        registerReceiver(receiver, intentFilter)


    }

    private fun timeLookUp() { //implementation 'commons-net:commons-net:3.6'
        val timeServerUrl = "time-a.nist.gov"
        val timeClient = NTPUDPClient()
        val inetAddress = InetAddress.getByName(timeServerUrl)
        val timeInfo: TimeInfo = timeClient.getTime(inetAddress)
        val returnTime: Long = timeInfo.message.receiveTimeStamp.time
        val ntpTime = Date(returnTime)
        val deviceTime = Date(System.currentTimeMillis
    }
}
