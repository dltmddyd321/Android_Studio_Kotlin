package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var tabRoot: TabLayout
    lateinit var fragment1: Fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment1 = Fragment1()

        tabRoot = findViewById(R.id.tabRoot)
        tabRoot.removeAllTabs()
        tabRoot.addTab(tabRoot.newTab().setText("1번"))
        tabRoot.addTab(tabRoot.newTab().setText("2번"))
        tabRoot.addTab(tabRoot.newTab().setText("3번"))
        tabRoot.addTab(tabRoot.newTab().setText("4번"))

        supportFragmentManager.beginTransaction().replace(R.id.tab_container, fragment1).commit()

        tabRoot.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> supportFragmentManager.beginTransaction().replace(R.id.tab_container, fragment1).commit()
                    else -> Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}