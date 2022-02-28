package com.example.viewdemo

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.viewdemo.databinding.RightFragmentLayoutBinding
import com.example.viewdemo.view.StickerView


class RightFragment : Fragment(), StickerView.OnStickerListener {
    private var binding: RightFragmentLayoutBinding? = null
    private lateinit var bubbleView: TextView
    private lateinit var triangleView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RightFragmentLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        bubbleView = TextView(requireContext())
        bubbleView.text = resources.getString(R.string.bubble_text)
        bubbleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        bubbleView.gravity = Gravity.CENTER
        bubbleView.setPadding(10, 10, 10, 10)
        bubbleView.background =
            ResourcesCompat.getDrawable(
                requireContext().resources,
                R.drawable.bubble_background,
                null
            )
        bubbleView.visibility = View.VISIBLE

        triangleView = ImageView(requireContext())
        triangleView.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.triangle,
                null
            )
        )
        triangleView.visibility = View.VISIBLE

        binding?.let {
            it.sticker.setOnStickerListener(this)
            it.root.addView(
                bubbleView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            it.root.addView(triangleView, 50, 50)
        }
    }

    override fun onClick() {
        bubbleView.visibility = View.VISIBLE
        trackTargetView(binding!!.sticker)
    }

    private fun trackTargetView(targetView: View) {
        binding?.let {
            var destX = targetView.left + targetView.width / 2
            var destY = targetView.bottom
            var startX = bubbleView.left + bubbleView.width / 2
            var startY = bubbleView.top
            var offSetX = destX - startX
            var offSetY = destY - startY + triangleView.height
            if (bubbleView.left + offSetX <= it.root.left) {
                offSetX = it.root.left - bubbleView.left
            }
            if (bubbleView.right + offSetX >= it.root.right) {
                offSetX = it.root.right - bubbleView.right
            }
            if (bubbleView.bottom + offSetY >= it.root.bottom) {
                offSetY = targetView.top - bubbleView.bottom - triangleView.height
            }
            bubbleView.offsetLeftAndRight(offSetX)
            bubbleView.offsetTopAndBottom(offSetY)

            triangleView.rotation = 180F
            startX = triangleView.left + triangleView.width / 2
            startY = triangleView.top
            destX = bubbleView.left + bubbleView.width / 2
            destY =
                if (bubbleView.top < binding!!.sticker.top)
                    bubbleView.bottom
                else {
                    triangleView.rotation = 0F
                    binding!!.sticker.bottom
                }
            triangleView.offsetTopAndBottom(destY - startY)
            triangleView.offsetLeftAndRight(destX - startX)
        }
    }
}