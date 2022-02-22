package com.example.viewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewdemo.adapter.NormalAdapter
import com.example.viewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = NormalAdapter(this)
    }
}