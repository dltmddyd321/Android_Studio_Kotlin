package com.example.widgettestproject.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.widgettestproject.MainActivity

class CheckCountWidget : GlanceAppWidget() {

    companion object {
        //위젯 데이터를 저장할 키 값
        val CLICK_COUNT_KEY = intPreferencesKey("CLICK_COUNT")
    }

    override val stateDefinition = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val prefs = currentState<Preferences>()
            val clickCount = prefs[CLICK_COUNT_KEY] ?: 0
            CountCheckContent(clickCount)
        }
    }

    @SuppressLint("RestrictedApi")
    @Composable
    private fun CountCheckContent(count: Int) {
        Column(
            modifier = GlanceModifier
                .size(300.dp)
                .padding(16.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Button clicked: $count times",
                style = TextStyle(
                    color = ColorProvider(android.R.color.black),
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = GlanceModifier.height(16.dp))

            Button(text = "Click Me", onClick = actionRunCallback<ClickActionCallback>())
        }
    }

    class ClickActionCallback : ActionCallback {
        override suspend fun onAction(
            context: Context,
            glanceId: GlanceId,
            parameters: ActionParameters
        ) {
            var cnt = -1

            updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) { prefs ->
                val currentCount = prefs[CLICK_COUNT_KEY] ?: 0
                prefs.toMutablePreferences().apply {
                    cnt = currentCount + 1
                    set(CLICK_COUNT_KEY, cnt)
                }
            }

            if (cnt % 5 == 0) {
                context.startActivity(
                    Intent(context, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                )
            }

            CheckCountWidget().update(context, glanceId)
        }
    }
}