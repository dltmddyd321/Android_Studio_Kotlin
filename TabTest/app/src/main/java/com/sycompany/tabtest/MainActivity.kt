package com.sycompany.tabtest

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.ComponentName
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsCallback
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CustomTabsClient.bindCustomTabsService(
            applicationContext,
            "com.android.chrome",
            tabConnection)
    }

    private val tabConnection: CustomTabsServiceConnection =
        object : CustomTabsServiceConnection() {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onCustomTabsServiceConnected(
                componentName: ComponentName,
                customTabsClient: CustomTabsClient
            ) {
                val session = customTabsClient.newSession(object : CustomTabsCallback() {
                    override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
                        super.onNavigationEvent(navigationEvent, extras)
                        when (navigationEvent) {
                            NAVIGATION_STARTED -> Log.e("psbs", "NAVIGATION_STARTED")
                            NAVIGATION_FINISHED -> Log.e("psbs", "NAVIGATION_FINISHED")
                            NAVIGATION_FAILED -> Log.e("psbs", "NAVIGATION_FAILED")
                            NAVIGATION_ABORTED -> Log.e("psbs", "NAVIGATION_ABORTED")
                        }
                    }
                })
                val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_launcher_background)
                val customTabsIntentBuilder = CustomTabsIntent.Builder(session)
                val menuItemTitle = "my menu"
                val actionIntent = Intent(
                    applicationContext, MainActivity::class.java
                )
                val menuItemPendingIntent = PendingIntent.getActivity(
                    applicationContext, 1, actionIntent, PendingIntent.FLAG_IMMUTABLE
                )
                customTabsIntentBuilder.addMenuItem(menuItemTitle, menuItemPendingIntent)
                customTabsIntentBuilder.setShowTitle(true)
                customTabsIntentBuilder.setCloseButtonIcon(bitmap)
                val customTabsIntent = customTabsIntentBuilder.build()
                customTabsIntent.intent.setPackage("com.android.chrome")
                customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                customTabsIntent.launchUrl(
                    applicationContext,
                    Uri.parse("https://psbs.tistory.com")
                )
            }

            override fun onServiceDisconnected(name: ComponentName) {}
        }
}