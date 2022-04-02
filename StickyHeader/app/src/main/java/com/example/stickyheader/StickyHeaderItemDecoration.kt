package com.example.stickyheader

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StickyHeaderItemDecoration(private val sectionCallback : SectionCallback) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        //첫 번째 위치 인덱스 값 반환
        val topChild = parent.getChildAt(0) ?: return

        //RecyclerView의 특정 위치 값을 반환
        val topChildPosition = parent.getChildAdapterPosition(topChild)
        if(topChildPosition == RecyclerView.NO_POSITION) {
            return
        }

        val currentHeader: View = sectionCallback.getHeaderLayoutView(parent, topChildPosition) ?: return

        fixLayoutSize(parent, currentHeader, topChild.measuredHeight)

        val contactPoint = currentHeader.bottom
        //바로 아래
        val childInContact : View = getChildInContact(parent, contactPoint) ?: return

        val childAdapterPosition = parent.getChildAdapterPosition(childInContact)
        if(childAdapterPosition == -1)
            return

        when {
            sectionCallback.isHeader(childAdapterPosition) ->
                moveHeader(c, currentHeader, childInContact)
            else ->
                drawHeader(c, currentHeader)
        }
    }

    private fun getChildInContact(parent: RecyclerView, contactPoint : Int) : View? {
        var childInContact : View ?= null
        for(i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            if(child.bottom > contactPoint) {
                if(child.top <= contactPoint) {
                    childInContact = child
                    break
                }
            }
        }
        return childInContact
    }

    private fun moveHeader(c : Canvas, currentHeader : View, nextHeader : View) {
        c.save()
        c.translate(0f, nextHeader.top - currentHeader.height.toFloat())
        currentHeader.draw(c)
        c.restore()
    }

    private fun drawHeader(c : Canvas, header : View) {
        c.save()
        c.translate(0f, 0f)
        header.draw(c)
        c.restore()
    }

    private fun fixLayoutSize(parent : ViewGroup, view: View, height : Int) {
        //onMeasure 또는 외부에서 그려지기 전 View 크기를 구할 경우, MeasureSpec 사용
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            parent.width,
            View.MeasureSpec.EXACTLY
        )

        val heightSpec = View.MeasureSpec.makeMeasureSpec(
            parent.height,
            View.MeasureSpec.EXACTLY
        )

        //getChilMeasureSpec : 특정 자식 View의 MeasureSpec 생성
        val childWidth : Int = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )

        val childHeight : Int = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            height
        )

        view.measure(childWidth, childHeight)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }

    interface SectionCallback {
        fun isHeader(position: Int): Boolean
        fun getHeaderLayoutView(list: RecyclerView, position: Int): View?
    }
}