package com.example.viewdemo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.example.viewdemo.R

class BubbleLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object{
        const val LEFT = 1
        const val TOP = 2
        const val RIGHT = 3
        const val BOTTOM = 4
    }

    private var lastX = 0F
    private var lastY = 0F
    private var radius = 0F
    private var direction = 0
    private val triangleHeight = 20F
    private var offSet = 0F
    private val borderPaint = Paint()
    private val oval = RectF()

    init {
        val parser = context.obtainStyledAttributes(attrs, R.styleable.BubbleLayout)
        background = ResourcesCompat.getDrawable(resources,R.drawable.bubble_background,null)
        direction = parser.getInt(R.styleable.BubbleLayout_direction, BOTTOM)
        radius = parser.getDimensionPixelSize(R.styleable.BubbleLayout_android_radius, 0).toFloat()
        offSet = parser.getFloat(R.styleable.BubbleLayout_android_offset,0F)
        borderPaint.isAntiAlias = true
        setWillNotDraw(false)
        parser.recycle()
    }




   /* fun drawTopTriangle(canvas: Canvas?) {
        path.addRoundRect(rect, radius, radius, Path.Direction.CCW)
        path.moveTo(pointX + triangleHeight / 2, pointY)
        path.lineTo(pointX, pointY - triangleHeight)
        path.lineTo(pointX - triangleHeight / 2, pointY)
        path.close()
        canvas?.drawPath(path, borderPaint)
    }

    fun drawRightTriangle(canvas: Canvas?) {
        path.addRoundRect(rect, radius, radius, Path.Direction.CCW)
        path.moveTo(pointX - triangleHeight / 2, pointY)
        path.lineTo(pointX, pointY + triangleHeight)
        path.lineTo(pointX + triangleHeight / 2, pointY)
        path.close()
        canvas?.drawPath(path, borderPaint)
    }

    fun drawBottomTriangle(canvas: Canvas?){
        path.addRoundRect(rect, radius, radius, Path.Direction.CCW)
        path.moveTo(pointX - triangleHeight / 2, pointY)
        path.lineTo(pointX, pointY + triangleHeight)
        path.lineTo(pointX + triangleHeight / 2, pointY)
        path.close()
        canvas?.drawPath(path, borderPaint)
    }*/


    fun onTargetBottom(targetView: View, offset: Int) {
        val targetLocation = IntArray(2)
        val location = IntArray(2)
        targetView.getLocationOnScreen(targetLocation)
        getLocationOnScreen(location)
        val destX = targetLocation[0] + targetView.width / 2
        val destY = targetLocation[1] + targetView.height + offset
        val startX = location[0] + width / 2
        val startY = location[1]
        offsetLeftAndRight(destX - startX)
        offsetTopAndBottom(destY - startY)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = it.x
                    lastY = it.y
                }
                MotionEvent.ACTION_MOVE -> {
                    //获取偏移量
                    val offSetX = it.x - lastX
                    val offSetY = it.y - lastY
                    layout(left + offSetX.toInt(), top + offSetY.toInt(), right + offSetX.toInt(), bottom + offSetY.toInt())
                }
            }
        }
        return true
    }
}