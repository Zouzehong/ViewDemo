package com.example.viewdemo.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

class StickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr)  {

    private var lastX = 0F
    private var lastY = 0F
    private var  listener : OnStickerListener? = null


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = it.x
                    lastY = it.y
                    listener?.onClick()
                }
                MotionEvent.ACTION_MOVE -> {
                    //获取偏移量
                    val offSetX = it.x - lastX
                    val offSetY = it.y - lastY
                    layout(left + offSetX.toInt(), top + offSetY.toInt(), right + offSetX.toInt(), bottom + offSetY.toInt())
                }
                else ->{
                }
            }
        }
        return true
    }

    fun setOnStickerListener(listener: OnStickerListener){
        this.listener = listener
    }

    interface OnStickerListener{
        fun onClick()
    }
}