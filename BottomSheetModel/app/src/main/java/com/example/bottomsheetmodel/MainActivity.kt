package com.example.bottomsheetmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val bottomSheet : LinearLayout by lazy {
        findViewById(R.id.bottom_sheet)
    }

    lateinit var sheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fragment BottomSheet
        val fragment = BottomSheetFragment()
        fragment.show(supportFragmentManager, fragment.tag)

        //Persistent Bottom Sheet
        sheetBehavior = BottomSheetBehavior.from(bottomSheet)
        sheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
               when(newState) {
                   BottomSheetBehavior.STATE_COLLAPSED -> {
                       Toast.makeText(this@MainActivity, "최소화!!", Toast.LENGTH_SHORT).show()
                   }
                   BottomSheetBehavior.STATE_DRAGGING -> {
                       Toast.makeText(this@MainActivity, "드래그 중!!", Toast.LENGTH_SHORT).show()
                   }
                   BottomSheetBehavior.STATE_EXPANDED -> {
                       Toast.makeText(this@MainActivity, "확장!!", Toast.LENGTH_SHORT).show()
                   }
                   BottomSheetBehavior.STATE_HIDDEN -> {
                       Toast.makeText(this@MainActivity, "숨기기!!", Toast.LENGTH_SHORT).show()
                   }
                   BottomSheetBehavior.STATE_SETTLING -> {
                       Toast.makeText(this@MainActivity, "STATE_SETTLING!!", Toast.LENGTH_SHORT).show()
                   }
               }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                Toast.makeText(this@MainActivity, "슬라이드 중!!", Toast.LENGTH_SHORT).show()
            }
        })
        button_open.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        button_close.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }
}