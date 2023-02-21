package com.example.changelanguage

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LocaleWrapper {
    private var sLocale: Locale? = null

    fun wrap(context: Context): Context {
        if (sLocale == null) {
            return context
        }
        val config: Configuration = context.resources.configuration
        config.setLocale(sLocale)
        return context.createConfigurationContext(config)
    }

    fun setLocale(lang: String) {
        sLocale = Locale(lang)
    }
}