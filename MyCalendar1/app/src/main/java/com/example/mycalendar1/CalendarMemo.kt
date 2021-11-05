package com.example.mycalendar1

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView

class CalendarMemo : LinearLayout {

    private var memoText = ""
    lateinit var dateTextView: TextView

    constructor(context: Context, str: String) : super(context) {
        this.memoText = str
        drawText(context)
    }

    constructor(context: Context, attrs: AttributeSet?, str: String) : super(context, attrs) {
        this.memoText = str
        drawText(context)
    }

    constructor(context: Context, attrs: AttributeSet?, str: String, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.memoText = str
        drawText(context)
    }

    private fun drawText(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.item_memo, null, false)
        dateTextView = view.findViewById(R.id.memoItem)
        dateTextView.text = memoText
    }
}