package com.example.clocktypebutton

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import java.security.AlgorithmConstraints

class MainActivity : AppCompatActivity() {

    private lateinit var imgBtnCenter : ImageButton
    private lateinit var constraintLayoutMenu : ConstraintLayout
    private lateinit var menu1 : LinearLayout
    private lateinit var menu2 : LinearLayout
    private lateinit var menu3 : LinearLayout
    private lateinit var menu4 : LinearLayout
    private var viewList = mutableListOf<LinearLayout>()
    private var menuTitleList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuTitleList.add("Post")
        menuTitleList.add("Camera")
        menuTitleList.add("Under")
        menuTitleList.add("Good")

        findView()
        setListener()
    }

    private fun findView() {

        imgBtnCenter = findViewById(R.id.CenterImage)
        constraintLayoutMenu = findViewById(R.id.constraintLayoutMenu)
        menu1 = findViewById(R.id.menu1)
        menu2 = findViewById(R.id.menu2)
        menu3 = findViewById(R.id.menu3)
        menu4 = findViewById(R.id.menu4)

        viewList.add(menu1)
        viewList.add(menu2)
        viewList.add(menu3)
        viewList.add(menu4)



    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListener() {
        imgBtnCenter.setOnClickListener {
            toggleCircleMenu()
        }

        val listener = createTouchListener()
        constraintLayoutMenu.setOnTouchListener(listener)

        for(view in viewList) {
            view.setOnTouchListener(listener)
        }
    }

    private fun createTouchListener() : View.OnTouchListener {
        return object : View.OnTouchListener {
            var x : Float = 0f
            var isTouched = false
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                when(event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        event.let {
                            x = it.x
                        }

                        return if (v is LinearLayout || v is ConstraintLayout) {
                            isTouched = true
                            true
                        } else {
                            isTouched = false
                            false
                        }
                    }

                    MotionEvent.ACTION_MOVE -> {
                        return true
                    }

                    MotionEvent.ACTION_UP -> {
                        val result = event.x - x

                        when {
                            result > 100 -> {
                                Log.d("???", "Flip Right")

                                for(linearLayout in viewList) {
                                    startRotate(linearLayout, false)
                                }
                            }
                            result < -100 -> {
                                Log.d("???", "Flip Right")

                                for(linearLayout in viewList) {
                                    startRotate(linearLayout, false)
                                }
                            }
                            else -> {
                                Log.d("???", "Flip Right")

                                if(v is LinearLayout) {
                                    toggleClick(v)
                                }
                            }
                        }
                        return false
                    } else -> {
                        return false
                    }
                }
                return false
            }
        }
    }

    private fun toggleClick(currentView : LinearLayout) {
        for(i in 0 until viewList.size) {
            if(currentView.id == viewList[i].id) {
                Toast.makeText(this, "Click Menu!!${menuTitleList[i]}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startRotate(currentView : LinearLayout, isLeft : Boolean) {
        val layoutParams = currentView.layoutParams as ConstraintLayout.LayoutParams
        val currentAngle = layoutParams.circleAngle

        val targetAngle = currentAngle + if(isLeft) {
            -45
        } else {
            45
        }

        val angleAnimator = ValueAnimator.ofFloat(currentAngle, targetAngle)

        angleAnimator.addUpdateListener {
            layoutParams.circleAngle = it.animatedValue as Float
            currentView.layoutParams = layoutParams
        }

        angleAnimator.duration = 400
        angleAnimator.interpolator = AnticipateOvershootInterpolator()
        angleAnimator.start()
    }

    private fun toggleCircleMenu() {
        if(constraintLayoutMenu.visibility == View.VISIBLE) {
            constraintLayoutMenu.visibility = View.GONE
        }
        else {
            constraintLayoutMenu.visibility = View.VISIBLE
        }
    }
}