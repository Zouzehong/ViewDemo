package com.example.viewdemo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.viewdemo.R
import kotlin.math.min
import kotlin.Int as Int


class CircleProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val circlePaint = Paint()
    private val arcPaint = Paint()
    private val textPaint = Paint()
    private var radius = 0F
    private val minRadius = 50F
    private var progress = 0
    private val targetProgress = 100F
    private val rect = RectF()
    private val bounds = Rect()

    init {
        val parser = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar)

        circlePaint.color = Color.GRAY
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeWidth = 10F
        circlePaint.isAntiAlias = true

        arcPaint.color = parser.getColor(R.styleable.CircleProgressBar_android_color, Color.GREEN)
        arcPaint.style = Paint.Style.STROKE
        arcPaint.strokeWidth = 20F
        arcPaint.isAntiAlias = true
        arcPaint.strokeCap = Paint.Cap.ROUND

        textPaint.color = arcPaint.color
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.isAntiAlias = true


        parser.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = min(getRealSize(widthMeasureSpec), getRealSize(heightMeasureSpec))
        setMeasuredDimension(size, size)
    }

    private fun getRealSize(measureSpec: Int) =
        if (MeasureSpec.getMode(measureSpec) == MeasureSpec.AT_MOST
            || MeasureSpec.getMode(measureSpec) == MeasureSpec.UNSPECIFIED
        ) {
            radius = minRadius
            (minRadius * 2 + arcPaint.strokeWidth).toInt()
        } else {
            MeasureSpec.getSize(measureSpec)
        }

    private fun initRect() {
        val diameter = radius * 2
        val left = (width - diameter) / 2F
        val top = (height - diameter) / 2F
        val right = left + diameter
        val bottom = top + diameter
        rect.set(left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        //这里targetProgress必须用float类型，不然就是整除运算结果为0
        val angle = (progress / targetProgress) * 360
        textPaint.getTextBounds("$progress", 0, "$progress".length, bounds)
        //中点坐标等于两点坐标相加除2，所以是(bounds.top + bounds.bottom) / 2
        val offSet = (bounds.top + bounds.bottom) / 2
        canvas?.let {
            it.drawCircle(width / 2F, height / 2F, radius, circlePaint)
            it.drawArc(rect, -90F, angle, false, arcPaint)
            // 基线的纵坐标可以理解为height/2 - (0 - offSet)，这里0 - offSet指文字坐标系下中点坐标到基线的距离
            it.drawText("${progress}%", width / 2F, height / 2F - offSet, textPaint)
        }
        if (progress < targetProgress) {
            progress++
            invalidate()
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        initRect()
        radius = (width - arcPaint.strokeWidth) / 2
        textPaint.textSize = width * 0.15F
    }

    fun reset() {
        progress = 0
        invalidate()
    }
}