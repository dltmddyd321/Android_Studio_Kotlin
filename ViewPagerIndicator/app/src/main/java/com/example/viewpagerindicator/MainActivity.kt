package com.example.viewpagerindicator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { PagerAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpMainActivity.adapter = adapter

        vpMainActivity.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                ciMainActivity.selectDot(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })

        ciMainActivity.createDotPanel(3, R.drawable.indicator_dot_off, R.drawable.indicator_dot_on, 0)
    }
}