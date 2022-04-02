package com.example.sheetpagerview

import androidx.fragment.app.*

//deprecated
class FragmentAdapter(fa: FragmentManager) : FragmentPagerAdapter(fa, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        const val NUM_ITEMS = 3
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return OneFragment()
            1 -> return TwoFragment()
        }
        return OneFragment()
    }

    override fun getCount(): Int {
        return  NUM_ITEMS
    }
}