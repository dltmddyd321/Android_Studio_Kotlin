package com.sycompany.chrometab

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsCallback
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = Uri.parse("http://gagebu.hosoft.kr/?bbs_tb=bbs&bbs_kind_i=4&t=bbs_view&id=5108")

        val intentBuilder = CustomTabsIntent.Builder()
        intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.purple_200))

        val customTabsIntent = intentBuilder.build()

        customTabsIntent.launchUrl(this, uri)
//        CustomTabsClient.bindCustomTabsService(
//            applicationContext,
//            "com.android.chrome",
//            tabConnection
//        )
    }

    private val tabConnection = object : CustomTabsServiceConnection() {
        override fun onServiceDisconnected(name: ComponentName) {}

        @SuppressLint("UnspecifiedImmutableFlag")
        override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
            val session = client.newSession(object : CustomTabsCallback() {
                override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
                    super.onNavigationEvent(navigationEvent, extras)
                    when (navigationEvent) {
                        NAVIGATION_STARTED -> Log.e("psbs", "NAVIGATION_STARTED")
                        NAVIGATION_FINISHED -> Log.e("psbs", "NAVIGATION_FINISHED")
                        NAVIGATION_FAILED -> Log.e("psbs", "NAVIGATION_FAILED")
                        NAVIGATION_ABORTED -> Log.e("psbs", "NAVIGATION_ABORTED")
                        else -> Log.e("psbs", "ERROR!")
                    }
                }
            })

            val customTabsIntentBuilder = CustomTabsIntent.Builder(session)

            val menuItemTitle = "my menu"
            val actionIntent = Intent(
                applicationContext, MainActivity::class.java
            )
            val menuItemPendingIntent = PendingIntent.getActivity(
                applicationContext, 1, actionIntent, 0
            )

            customTabsIntentBuilder.addMenuItem(menuItemTitle, menuItemPendingIntent)

            val customTabsIntent = customTabsIntentBuilder.build()
//            customTabsIntent.intent.setPackage("com.android.chrome")
//            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            customTabsIntent.launchUrl(applicationContext, Uri.parse("https://www.naver.com/"))
        }
    }
}