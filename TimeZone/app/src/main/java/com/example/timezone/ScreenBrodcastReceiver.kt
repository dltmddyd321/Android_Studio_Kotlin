package com.example.timezone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ScreenBrodcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1?.action?.equals(Intent.ACTION_SCREEN_ON) == true) {
            Toast.makeText(App.instance, "Open!!", Toast.LENGTH_SHORT).show()
        }
    }
}