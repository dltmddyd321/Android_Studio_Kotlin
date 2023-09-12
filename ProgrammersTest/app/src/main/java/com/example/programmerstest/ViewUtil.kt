package com.example.programmerstest

import android.annotation.SuppressLint
import android.os.Looper
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.View

fun View.setOnCustomLongClickListener(
    duration: Long,
    consumeEvent: Boolean = true,
    listener: View.OnLongClickListener
) {
    setOnTouchListener(object : View.OnTouchListener {

        private val handler = android.os.Handler(Looper.getMainLooper())
        private var hasFired = false

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    this@setOnCustomLongClickListener.isPressed = true
                    hasFired = false
                    handler.postDelayed(
                        {
                            performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                            hasFired = listener.onLongClick(this@setOnCustomLongClickListener)
                        }, duration
                    )
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    this@setOnCustomLongClickListener.isPressed = false
                    handler.removeCallbacksAndMessages(null)
                    if (!hasFired) {
                        this@setOnCustomLongClickListener.callOnClick()
                    }
                }
                else -> {}
            }
            return consumeEvent
        }
    })
}