package com.example.widgetbasic

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import org.w3c.dom.Text

class TextWidget : AppWidgetProvider() {

    var inputValues: String? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        inputValues = intent?.getStringExtra("number")

        context?.let {
            var appWidgetManager = AppWidgetManager.getInstance(context)
            var widgetName = ComponentName(context.packageName, TextWidget::class.java.name)
            var widgetIds = appWidgetManager.getAppWidgetIds(widgetName)
            var actionName = intent?.action

            actionName?.let {
                if(actionName == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
                    widgetIds?.let {
                        if(widgetIds.isNotEmpty()) {
                            this.onUpdate(context, appWidgetManager, widgetIds)
                        }
                    }
                }
            }
        }
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        context?.let {
            val views = RemoteViews(context.packageName, R.layout.text_widget)
            views.setTextViewText(R.id.tv_text, "$inputValues ")
            appWidgetManager?.updateAppWidget(appWidgetIds, views)
        }
    }
}