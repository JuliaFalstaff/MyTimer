package com.example.timer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.timer.model.TimestampProvider
import com.example.timer.utils.TimesMillisecondsFormatter
import com.example.timer.usecase.ElapsedTimeCalculator
import com.example.timer.usecase.StopwatchStateCalculator
import com.example.timer.usecase.StopwatchStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainViewModel() : ViewModel() {

    private var viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestratorFirst = StopWatchOrchestrator(
            StopwatchStateHolder(
                    StopwatchStateCalculator(
                            timestampProvider,
                            ElapsedTimeCalculator(timestampProvider)
                    ),
                    ElapsedTimeCalculator(timestampProvider),
                    TimesMillisecondsFormatter()
            ),
            viewModelScope
    )

    val liveData: LiveData<String> = stopwatchListOrchestratorFirst.ticker.asLiveData()

    fun pause() {
        stopwatchListOrchestratorFirst.pause()
    }

    fun start() {
        stopwatchListOrchestratorFirst.start()
    }

    fun stop() {
        stopwatchListOrchestratorFirst.stop()
    }
}