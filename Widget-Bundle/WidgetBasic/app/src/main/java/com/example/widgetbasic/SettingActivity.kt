package com.example.widgetbasic

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    var widgetId : Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val bundle = intent.extras
        bundle?.let {
            widgetId = it.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }
        setUi()
    }

    private fun setUi() {
        btn_change.setOnClickListener {
            val appWidgetManager = AppWidgetManager.getInstance(this)
            val remoteView = RemoteViews(packageName, R.layout.text_widget)
            remoteView.setTextViewText(R.id.tv_text, text_change.text)
            appWidgetManager?.updateAppWidget(widgetId!!, remoteView)

            var intent = Intent()
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}