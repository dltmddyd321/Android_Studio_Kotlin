package com.example.mousepointcustom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), View.OnTouchListener {
//    constructor(context: Context) : this(context, null)
//    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
//    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs)

    var paint : Paint?= null
    var xAxis: Float?= null
    var yAxis: Float?= null

    init {
        paint = Paint()
        xAxis = -100f
        yAxis = -100f
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_UP -> {
                xAxis = -100f
                yAxis = -100f
                invalidate()
            }
            MotionEvent.ACTION_DOWN -> {
                xAxis = event.x
                yAxis = event.y
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint?.style = Paint.Style.STROKE
        paint?.strokeWidth = 5F
        paint?.color = Color.BLUE
        canvas?.drawCircle(xAxis!!, yAxis!!, 50f, paint!!)
    }
}