package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout

class TbLogo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: TbLogoViewBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            as LayoutInflater
        binding = TbLogoViewBinding.inflate(inflater, this, true)
    }

    fun setText(data: String) {
        binding.tbtext.text = data
    }
}