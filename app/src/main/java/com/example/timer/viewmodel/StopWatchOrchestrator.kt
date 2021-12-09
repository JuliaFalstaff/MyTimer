package com.example.timer.viewmodel

import com.example.timer.usecase.StopwatchStateHolder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StopWatchOrchestrator(
        private val stopwatchStateHolder: StopwatchStateHolder,
        private val scope: CoroutineScope,
) {
    private var job: Job? = null
    private val mutableTicker = MutableStateFlow(EMPTY_STRING)
    val ticker: StateFlow<String>
        get() = mutableTicker

    fun start() {
        if (job == null) startJob()
        stopwatchStateHolder.start()
    }

    private fun startJob() {
        scope.launch {
            while (isActive) {
                mutableTicker.value = stopwatchStateHolder.getStringTime()
                delay(DELAY_TIME)
            }
        }
    }

    fun pause() {
        stopwatchStateHolder.pause()
        stopJob()
    }

    fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = DEFAULT_TIME
    }

    companion object {
        private const val DEFAULT_TIME = "00:00:000"
        private const val DELAY_TIME: Long = 20
        private const val EMPTY_STRING = ""
    }
}