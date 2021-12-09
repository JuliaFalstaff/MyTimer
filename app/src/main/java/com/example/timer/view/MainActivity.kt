package com.example.timer.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.timer.databinding.ActivityMainBinding
import com.example.timer.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.liveData.observe(this) {
            binding.textTime.text = it
        }
        initButtons()
    }

    private fun initButtons() = with(binding) {
        buttonStart.setOnClickListener { viewModel.start() }
        buttonPause.setOnClickListener { viewModel.pause() }
        buttonStop.setOnClickListener { viewModel.stop() }
    }
}