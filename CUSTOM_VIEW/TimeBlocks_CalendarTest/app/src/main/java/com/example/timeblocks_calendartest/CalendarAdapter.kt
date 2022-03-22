package com.example.timeblocks_calendartest

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class CalendarAdapter(context: Context, days: ArrayList<Date>, eventDays: HashSet<Date>, inputMonth:Int) : ArrayAdapter<Date>(context, R.layout.calendar_layout, days) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val inputMonth = inputMonth - 1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val calendar = Calendar.getInstance()
        val date = getItem(position)

        calendar.time = date
        val day = calendar.get(Calendar.DATE)
        val month = calendar.get(Calendar.DATE)
        val year = calendar.get(Calendar.DATE)

        val today = Date()
        val calendarToday = Calendar.getInstance()
        calendarToday.time = today

        if(view == null) {
            view = inflater.inflate(R.layout.calendar_day_layout, parent, false)
        }

        (view as TextView).setTypeface(null, Typeface.NORMAL)
        view.setTextColor(Color.parseColor("#56a6a9"))

        if(month != inputMonth || year != calendarToday.get(Calendar.YEAR)) {
            view.visibility = View.INVISIBLE
        }

        if(month == calendarToday.get(Calendar.MONTH) && year == calendarToday.get(Calendar.YEAR) && day == calendarToday.get(Calendar.DATE)) {
            //오늘의 날짜에 적용할 알고리즘 구현
        }

        view.text = calendar.get(Calendar.DATE).toString()

        return view
    }
}