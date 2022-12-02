package com.sycompany.frescobasic

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class AppCore : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}