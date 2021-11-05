package com.example.mycalendar1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(context: Context) : RecyclerView.Adapter<ViewPagerAdapter.RecyclerViewHolder>() {

    private lateinit var calendarAdapter: CalendarAdapter
    private val context = context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_calendar, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.RecyclerViewHolder, position: Int) {
        calendarAdapter = CalendarAdapter(context, position)

        val layoutManager = GridLayoutManager(context, 7)
        holder.recyclerView.layoutManager = layoutManager
        holder.recyclerView.adapter = calendarAdapter
    }

    override fun getItemCount(): Int {
        return 12
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.calendarRecyclerView)
    }

}