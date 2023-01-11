package com.sycompany.chrometab

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.browser.customtabs.CustomTabsCallback
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection

fun isChromeEnabled(context: Context): Boolean
    = context.packageManager.getPackageInfo("com.android.chrome", 0).applicationInfo.enabled

fun openChromeWebService(context: Context, openUrl: String) {
    if (openUrl.isEmpty()) return
    val chromePackage = "com.android.chrome"

    val chromeTabConnection: CustomTabsServiceConnection =
        object : CustomTabsServiceConnection() {

            override fun onServiceDisconnected(name: ComponentName) {
                Log.i("CHROME","chrome Tab Service Disconnected")
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
                val session = client.newSession(object : CustomTabsCallback() {
                    override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
                        super.onNavigationEvent(navigationEvent, extras)
                        when (navigationEvent) {
                            NAVIGATION_STARTED -> Log.i("CHROME","chrome NAVIGATION_STARTED")
                            NAVIGATION_FINISHED -> Log.i("CHROME","chrome NAVIGATION_FINISHED")
                            NAVIGATION_FAILED -> Log.i("CHROME","chrome NAVIGATION_FAILED")
                            NAVIGATION_ABORTED -> Log.i("CHROME","chrome NAVIGATION_ABORTED")
                        }
                    }
                })

                val customTabsIntentBuilder = CustomTabsIntent.Builder(session)
                customTabsIntentBuilder.setShowTitle(true)

                val menuItemTitle = "my menu"
                val actionIntent = Intent(
                    context, MainActivity::class.java
                )
                val menuItemPendingIntent = PendingIntent.getActivity(
                    context, 1, actionIntent, PendingIntent.FLAG_IMMUTABLE
                )

                customTabsIntentBuilder.addMenuItem(menuItemTitle, menuItemPendingIntent)

                val customTabsIntent = customTabsIntentBuilder.build()
                customTabsIntent.intent.setPackage(chromePackage)
                customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                customTabsIntent.launchUrl(context, Uri.parse(openUrl))
            }
        }

    CustomTabsClient.bindCustomTabsService(context, chromePackage, chromeTabConnection)
}