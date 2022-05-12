package com.example.textutiltest

import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.style.LeadingMarginSpan

class IndentLeadingMarginSpan(private val indentDelimiters : List<String> = INDENT_DELIMITERS) : LeadingMarginSpan {

    companion object {
        private val INDENT_DELIMITERS = listOf("·", "ㆍ", "-", "┗")
    }

    private var indentMargin : Int = 0

    override fun getLeadingMargin(first: Boolean): Int = if(first) 0 else indentMargin

    override fun drawLeadingMargin(
        canvas: Canvas, paint: Paint, currentMarginLocation: Int, paragraphDirection: Int,
        lineTop: Int, lineBaseline: Int, lineBottom: Int, text: CharSequence, lineStart: Int,
        lineEnd: Int, isFirstLine: Boolean, layout: Layout
    ) {
        if(!isFirstLine) {
            return
        }

        //해당 쥴의 첫 2글자 가져오기
        val lineStartText =
            runCatching { text.substring(lineStart, lineStart + 2) }.getOrNull() ?: return

        indentMargin =
            if(indentDelimiters.contains(lineStartText.trimEnd())) {
                paint.measureText(lineStartText).toInt()
            } else {
                0
            }
    }
}