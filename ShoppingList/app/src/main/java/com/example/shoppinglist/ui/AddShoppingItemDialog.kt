package com.example.shoppinglist.ui

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context: Context, var dialogListener: AddDialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            dialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }
}

package com.day2life.timeblocks.application

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.day2life.timeblocks.R
import com.day2life.timeblocks.addons.timeblocks.TimeBlocksUser
import com.day2life.timeblocks.databinding.ViewToastBinding
import com.day2life.timeblocks.util.AdsPreLoader
import com.day2life.timeblocks.util.convertPixelsToDp


class AdToast(private val context: Context, private val text: String) : Dialog(context) {
    private lateinit var binding: ViewToastBinding

    companion object {
        //Toast.LENGTH_SHORT
        private const val DURATION_SHORT = 2000L
    }

    override fun onStart() {
        super.onStart()

        val decorView = window?.decorView

        val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
            decorView,
            PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f)
        )
        scaleDown.setDuration(DURATION_SHORT / 2)
        scaleDown.start()
    }

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewToastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val params = attributes
            params.gravity = Gravity.TOP
            params.y = AppScreen.dpToPx(70f)
            attributes = params
        }

        binding.textView.text = text
        binding.actionBtn.visibility = View.GONE
        setAdView()

        Handler(Looper.getMainLooper()).postDelayed({
            dismissDialog()
            AdsPreLoader.loadAds()
        }, DURATION_SHORT)
    }

    private fun setAdView() {
        if (TimeBlocksUser.getInstance().isPremium) return
        val nativeAd = AdsPreLoader.getAd()
        binding.toastView.setCardBackgroundColor(
            ContextCompat.getColor(context, R.color.toastBackground))
        binding.toastView.radius = convertPixelsToDp(120f)
        binding.toastView.elevation = 0f
        binding.toastAdMobView.visibility = View.VISIBLE
        binding.toastAdMobText.text = nativeAd?.body
        binding.toastAdMobView.bodyView = binding.toastAdMobView
        nativeAd?.let { binding.toastAdMobView.setNativeAd(it) }
    }

    private fun dismissDialog() {
        val decorView = window?.decorView

        val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
            decorView,
            PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f)
        )
        scaleDown.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {}
            override fun onAnimationEnd(p0: Animator) { dismiss() }
            override fun onAnimationCancel(p0: Animator) {}
            override fun onAnimationRepeat(p0: Animator) {}
        })
        scaleDown.setDuration(DURATION_SHORT / 2)
        scaleDown.start()
    }
}
