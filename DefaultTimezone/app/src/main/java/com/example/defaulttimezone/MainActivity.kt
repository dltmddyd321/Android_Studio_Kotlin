package com.example.defaulttimezone

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TimeZone Test", "기본 시간대 : ${DateFormat.getInstance().timeZone.id}")

        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"))
        val customCal = Calendar.getInstance()

        val systemLocale = getLocale(this)

        val systemCal = systemLocale?.let { Calendar.getInstance(it) }

        if (systemCal != null)  Log.d("TimeZone Test", "커스텀 : ${customCal.timeZone.id} / 시스템 : ${systemCal.timeZone.id}")
    }

    private fun getLocale(context: Context): Locale? {
        var locale: Locale?
        val basicTimezone = Settings.System.getString(this.contentResolver, Settings.Global.AUTO_TIME_ZONE)
        Log.d("TimeZone Test", "Basic : $basicTimezone")
        val configuration = Configuration()
        configuration.setToDefaults()
        Settings.System.getConfiguration(context.contentResolver, configuration)
        locale = configuration.locales.get(0)
        if (locale == null) {
            locale = Locale.getDefault()
        }
        Log.d("TimeZone Test", "Locale : ${locale?.country}")
        return locale
    }
}