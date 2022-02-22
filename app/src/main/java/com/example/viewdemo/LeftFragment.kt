package com.example.viewdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class LeftFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceStategit: Bundle?
    ): View? {
        return inflater.inflate(R.layout.left_fragment_layout, container, false)
    }
}