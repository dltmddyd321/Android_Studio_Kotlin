package com.example.varietyanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.varietyanimation.databinding.ActivityFadeBinding

class FadeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFadeBinding
    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFadeBinding.inflate(layoutInflater)
        val view : ConstraintLayout = binding.root
        setContentView(view)

        shortAnimationDuration = resources.getInteger(R.integer.material_motion_duration_short_1)
        binding.group.visibility = View.GONE

        binding.start.setOnClickListener {
            crossFade()
        }
    }

    private fun crossFade() {

        binding.group.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }

        binding.start.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    binding.start.visibility = View.GONE
                }
            })
    }
}