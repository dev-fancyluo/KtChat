package com.fancyluo.fancyim.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.fancyluo.fancyim.R
import org.jetbrains.anko.sp

/**
 * fancyLuo
 * date: 2017/11/27 18:58
 * desc:
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {

    private val letterArray = arrayOf(
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var textBaseline = 0f
    private var sectionWight = 0f
    private var sectionHeight = 0f

    private var listener: OnSlideSelectListener? = null

    init {
        paint.apply {
            textSize = sp(14).toFloat()
            // 横向居中文本
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // 获取绘制的起点
        sectionWight = w.toFloat()

        // 获取每个字母所分配的高度
        sectionHeight = (h / letterArray.size).toFloat()
        val fontMetrics = paint.fontMetrics
        // 获取绘制的字体高度
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        // 计算基准线：分配的高度的一半 + 字体高度的一半 - descent
        textBaseline = (sectionHeight / 2) + ((textHeight / 2) - fontMetrics.descent)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val x = (sectionWight / 2).toFloat()
        var baseline = textBaseline
        letterArray.forEach {
            canvas.drawText(it, x, baseline, paint)
            baseline += sectionHeight
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                setBackgroundResource(R.drawable.bg_slide_bar)
                listener?.onSelectLetter(letterArray[getSelectIndex(event)])
            }
            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.TRANSPARENT)
                listener?.onUnSelectLetter()
            }
            MotionEvent.ACTION_MOVE -> {
                listener?.onSelectLetter(letterArray[getSelectIndex(event)])
            }
        }
        return true
    }

    private fun getSelectIndex(event: MotionEvent): Int {
        val index = (event.y / sectionHeight).toInt()
        return when {
            index < 0 -> 0
            index > 25 -> 25
            else -> index
        }
    }

    fun setOnSlideSelectListener(listener: OnSlideSelectListener) {
        this.listener = listener
    }

    interface OnSlideSelectListener {
        fun onSelectLetter(letter: String)
        fun onUnSelectLetter()
    }

}