package com.example.viewdemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.viewdemo.LeftFragment
import com.example.viewdemo.RightFragment

class NormalAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    companion object {
        const val ITEM_COUNT = 2
        const val LEFT = 0
        const val RIGHT = 1
    }

    override fun getItemCount() = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            LEFT -> LeftFragment()
            RIGHT -> RightFragment()
            else -> LeftFragment()
        }
    }
}