package com.example.workoutProject.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.workoutProject.common.BaseFragmentFactory
import com.example.workoutProject.fragment.first.FirstFragment
import com.example.workoutproject.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    lateinit var pagerAdapter: PagerFragmentStateAdapter

    companion object {
        var instance: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = BaseFragmentFactory(Int.MAX_VALUE / 2)
        super.onCreate(savedInstanceState)
        instance = this
        setContentView(R.layout.activity_main)

        pagerAdapter = PagerFragmentStateAdapter(this)

        // 3개의 Fragment Add
        pagerAdapter.addFragment(FirstFragment())

        // ViewPager2 Adapter
        main_pager.isUserInputEnabled
        main_pager.adapter = pagerAdapter
        main_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("MainViewPager", "Page ${position + 1}")
            }
        })

        // TabLayout attach
        TabLayoutMediator(main_bottom_menu, main_pager) { tab, position ->
            when (position) {
                0 -> tab.text = "달력"
                1 -> tab.text = "루틴"
                2 -> tab.text = "운동"
                3 -> tab.text = "타/스"
                4 -> tab.text = "마이"
            }
        }.attach()
    }

    override fun onBackPressed() {
        val fragmentManager: FragmentManager = supportFragmentManager
        for (fragment in fragmentManager.fragments) {
            if (fragment.isVisible) {
                val chilFrag: FragmentManager = fragment.childFragmentManager
                if (chilFrag.backStackEntryCount > 0) {
                    chilFrag.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }
}