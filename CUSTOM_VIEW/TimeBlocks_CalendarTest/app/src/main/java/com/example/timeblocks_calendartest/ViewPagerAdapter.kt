package com.example.timeblocks_calendartest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class CustomAdapter: PagerAdapter {

    lateinit var context : Context
    private lateinit var layoutInflater: LayoutInflater

    constructor(container: Context) {
        this.context = context
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        pager.removeView(`object` as View)
    }

}