package com.example.sheetpagerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout

class BottomSheet : BottomSheetDialogFragment() {

    private lateinit var bottomSheet : ViewGroup
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var viewPager : ViewPager
    private lateinit var appBarLayout : AppBarLayout

    override fun onStart() {
        super.onStart()
        bottomSheet = dialog!!.findViewById(com.google.android.material.R.id.design_bottom_sheet) as ViewGroup
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if(BottomSheetBehavior.STATE_EXPANDED == newState) {
                    showView(appBarLayout, getActionBarSize())
                }
                if(BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    hideAppBar(appBarLayout)
                }
                if(BottomSheetBehavior.STATE_HIDDEN == newState) {
                    dismiss()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        hideAppBar(appBarLayout)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    //appBar 숨김 처리
    private fun hideAppBar(view: View) {
        val params = view.layoutParams
        params.height = 0
        view.layoutParams = params
    }

    //View 높이를 유동적으로 조절
    private fun showView(view: View, size: Int) {
        val params = view.layoutParams
        params.height = size
        view.layoutParams = params
    }

    @SuppressLint("Recycle")
    private fun getActionBarSize(): Int {
        val styleAttributes = context?.obtainStyledAttributes(intArrayOf(androidx.appcompat.R.attr.actionBarSize))

        return styleAttributes?.getDimension(0, 0f)!!.toInt()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myView : View = inflater.inflate(R.layout.layout_bottom_sheet, container, false)
        val tabLayout = myView.findViewById<TabLayout>(R.id.myTabLayout)
        appBarLayout = myView.findViewById(R.id.appBarLayout)
        viewPager = myView.findViewById(R.id.myViewPager)
        tabLayout.addTab(tabLayout.newTab().setText("First"))
        tabLayout.addTab(tabLayout.newTab().setText("Second"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = FragmentAdapter(childFragmentManager)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}

        })

        return myView
    }
}