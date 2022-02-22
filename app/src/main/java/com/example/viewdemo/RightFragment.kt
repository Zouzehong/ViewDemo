package com.example.viewdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.viewdemo.databinding.RightFragmentLayoutBinding


class RightFragment : Fragment() {
    private var binding: RightFragmentLayoutBinding? = null

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
        binding?.let {
            val bubbleBox = it.bubbleBox
            bubbleBox.left.text = resources.getString(R.string.bubble_content)
            bubbleBox.right.text = resources.getString(R.string.bubble_confirm)
            bubbleBox.root.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bubble1_background, null)
            bubbleBox.stroke.background =
                ResourcesCompat.getDrawable(resources, R.color.black, null)
            bubbleBox.root.setPadding(20, 40, 20, 20)
            bubbleBox.root.visibility = View.VISIBLE
            bubbleBox.right.setOnClickListener {
                bubbleBox.root.visibility = View.GONE
            }

            val cashBox = it.cashBox
            cashBox.root.visibility = View.VISIBLE
            cashBox.root.background =
                ResourcesCompat.getDrawable(resources, R.drawable.cash_box_background, null)
            cashBox.left.text = resources.getString(R.string.earn_cash)
            cashBox.right.text = resources.getString(R.string.bubble_content)
        }
    }
}