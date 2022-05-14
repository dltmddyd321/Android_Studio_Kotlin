package com.example.postservice.extension

import android.widget.TextView
import androidx.annotation.ColorRes

fun TextView.setTextColorRes(@ColorRes colorResId: Int) {
    setTextColor(color(colorResId))
}