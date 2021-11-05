package com.example.timeblocks_calendartest

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridView
import android.widget.LinearLayout
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class CalendarView : LinearLayout {
    lateinit var header: LinearLayout
    lateinit var gridView: GridView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        initControl(context, attrs!!)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun assignUiElements() {
        header = findViewById(R.id.calendar_header)
        gridView = findViewById (R.id.calendar_grid)
    }

    fun updateCalendar(events:HashSet<Date>, inputCalendar: Calendar) {
        val cells = ArrayList<Date>()

        inputCalendar.set(Calendar.DAY_OF_MONTH, 1)

        val monthBeginningCell = inputCalendar.get(Calendar.DAY_OF_WEEK) -1

        inputCalendar.add(Calendar.DAY_OF_WEEK, -monthBeginningCell)

        while (cells.size < (Calendar.DAY_OF_MONTH) +
                inputCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            cells.add(inputCalendar.time)
            inputCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        gridView.adapter = CalendarAdapter(context, cells, events, inputCalendar.get(Calendar.MONTH))

    }

    private fun initControl(context: Context, attrs: AttributeSet) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.calendar_layout, this)
        assignUiElements()
    }
}
