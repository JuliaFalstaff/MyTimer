package com.example.timer.usecase

import com.example.timer.model.AppState
import com.example.timer.utils.TimesMillisecondsFormatter

class StopwatchStateHolder(
        private val stopwatchStateCalculator: StopwatchStateCalculator,
        private val elapsedTimeCalculator: ElapsedTimeCalculator,
        private val timesmillisecFormatted: TimesMillisecondsFormatter,
) {
    var currentState: AppState = AppState.Paused(0)
        private set

    fun start() {
        currentState = stopwatchStateCalculator.calcRunningState(currentState)
    }

    fun pause() {
        currentState = stopwatchStateCalculator.calcPausedState(currentState)
    }

    fun stop() {
        currentState = AppState.Paused(0)
    }

    fun getStringTime(): String {
        val elapsedTime = when (val currentState = currentState) {
            is AppState.Paused -> currentState.elapsedTime
            is AppState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timesmillisecFormatted.format(elapsedTime)
    }
}