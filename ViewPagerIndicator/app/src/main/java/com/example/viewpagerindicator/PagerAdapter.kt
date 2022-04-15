package com.example.viewpagerindicator

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.viewpagerindicator.fragment.AFragment
import com.example.viewpagerindicator.fragment.BFragment
import com.example.viewpagerindicator.fragment.CFragment

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> AFragment()
            1 -> BFragment()
            else -> CFragment()
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}