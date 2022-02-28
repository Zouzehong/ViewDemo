package com.example.viewdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viewdemo.databinding.LeftFragmentLayoutBinding


class LeftFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceStategit: Bundle?
    ): View? {
        binding = LeftFragmentLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private var binding: LeftFragmentLayoutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        binding?.let {
            val circleProgressBar = it.circleProgressBar
            it.resetButton.setOnClickListener {
                circleProgressBar.reset()
            }
        }
    }
}