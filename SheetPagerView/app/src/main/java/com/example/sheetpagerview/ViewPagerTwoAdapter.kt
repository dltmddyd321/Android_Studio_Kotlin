package com.example.sheetpagerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ViewPagerTwoAdapter : RecyclerView.Adapter<ViewPagerTwoAdapter.MyPagerViewHolder>() {

    companion object {
        const val NUM_ITEMS = 3
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerTwoAdapter.MyPagerViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewPagerTwoAdapter.MyPagerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class MyPagerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }
}