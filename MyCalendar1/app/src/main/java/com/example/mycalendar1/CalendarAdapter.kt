package com.example.mycalendar1

import android.content.Context
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(context: Context, date: Int): RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    val context = context

    var dateCnt = 1
    var dateByCalendar = date
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarAdapter.CalendarViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CalendarAdapter.CalendarViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        //달력 한 페이지 당 일자 수
        return 35
    }

    inner class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nowDay = itemView.findViewById<TextView>(R.id.calendarNumber)
        val memoLinearLayout = itemView.findViewWithTag<LinearLayout>(R.id.memoSpaceLayout)

    }
}