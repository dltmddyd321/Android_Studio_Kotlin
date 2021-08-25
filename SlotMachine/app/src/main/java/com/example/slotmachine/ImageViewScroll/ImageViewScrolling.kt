package com.example.slotmachine.ImageViewScroll

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.slotmachine.R
import kotlinx.android.synthetic.main.img_view_slot.view.*
import java.util.jar.Attributes

class ImageViewScrolling: FrameLayout {

    internal lateinit var eventEnd: EventEnd

    internal var last_result = 0
    internal var oldvalue=0

    val value: Int
        get() = Integer.parseInt(nextImg.tag.toString())
    fun setEventEnd(eventEnd: EventEnd)
    {
        this.eventEnd = eventEnd
    }

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attrs:AttributeSet): super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.img_view_slot,this)

        nextImg.translationY = height.toFloat()
    }

    fun setValueRandom(image: Int, num_rotate:Int) {
        currentImg.animate()
            .translationY((-height).toFloat())
            .setDuration(animation.duration).start()

        nextImg.translationY = nextImg.height.toFloat()

        nextImg.animate().translationY(0f).setDuration(animation.duration.toLong())
            .setListener(object :Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    setImage(currentImg,oldvalue%6)
                    currentImg.translationY = 0f
                    if(oldvalue != num_rotate){
                        setValueRandom(image, num_rotate)
                        oldvalue++;
                    }
                    else {
                        last_result = 0
                        oldvalue = 0
                        setImage(nextImg,image)
                        eventEnd.eventEnd(image%6, num_rotate)
                    }
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationRepeat(animation: Animator?) {

                }

            }).start()
    }

    private fun setImage(img: ImageView? , value:Int) {
        if(value == Util.bar)
            img!!.setImageResource(R.drawable.bar_done)
        else if(value == Util.lemon)
            img!!.setImageResource(R.drawable.lemon_done)
        else if(value == Util.orange)
            img!!.setImageResource(R.drawable.orange_done)
        else if(value == Util.seven)
            img!!.setImageResource(R.drawable.sevent_done)
        else if(value == Util.triple)
            img!!.setImageResource(R.drawable.triple_done)
        else if(value == Util.watermelon)
            img!!.setImageResource(R.drawable.waternelon_done)

        img!!.tag = value
        last_result = value
    }
}


