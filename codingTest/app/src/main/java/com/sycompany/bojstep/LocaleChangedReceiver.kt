package com.sycompany.bojstep

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class LocaleChangedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_TIMEZONE_CHANGED -> {
                Toast.makeText(context, "시간대가 변경됨!", Toast.LENGTH_SHORT).show()
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this)
            }
        }
    }
}