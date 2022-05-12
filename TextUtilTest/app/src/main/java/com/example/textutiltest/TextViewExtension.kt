package com.example.textutiltest

import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("indentText")
fun TextView.setIndentLeadingMarginSpan(indentText: CharSequence?) {
    indentText ?: return

    this.text = SpannableStringBuilder(indentText).apply {
        setSpan(IndentLeadingMarginSpan(), 0, length, 0)
    }
}