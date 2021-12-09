package com.example.timer.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.timer.databinding.ActivityMainBinding
import com.example.timer.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModelFirstTimer: MainViewModel by inject()
    private val viewModelSecondTimer: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLiveDataObservers()
        initButtonsFirstTimer()
        initButtonsSecondTimer()
    }

    private fun initLiveDataObservers() {
        viewModelFirstTimer.liveData.observe(this) {
            binding.textTime.text = it
        }
        viewModelSecondTimer.liveData.observe(this) {
            binding.textTimeSecond.text = it
        }
    }

    private fun initButtonsSecondTimer() = with(binding) {
        buttonStartSecond.setOnClickListener { viewModelSecondTimer.start() }
        buttonPauseSecond.setOnClickListener { viewModelSecondTimer.pause() }
        buttonStopSecond.setOnClickListener { viewModelSecondTimer.stop() }
    }

    private fun initButtonsFirstTimer() = with(binding) {
        buttonStart.setOnClickListener { viewModelFirstTimer.start() }
        buttonPause.setOnClickListener { viewModelFirstTimer.pause() }
        buttonStop.setOnClickListener { viewModelFirstTimer.stop() }
    }
}