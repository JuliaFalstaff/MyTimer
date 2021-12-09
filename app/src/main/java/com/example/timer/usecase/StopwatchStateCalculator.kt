package com.example.timer.usecase

import com.example.timer.model.AppState
import com.example.timer.model.TimestampProvider

class StopwatchStateCalculator(
        private val timestampProvider: TimestampProvider,
        private val elapsedTimeCalculator: ElapsedTimeCalculator,

        ) {
    fun calcRunningState(oldState: AppState): AppState.Running =
            when (oldState) {
                is AppState.Running -> oldState
                is AppState.Paused -> {
                    AppState.Running(startTime = timestampProvider.getMilliseconds(),
                            elapsedTime = oldState.elapsedTime)
                }
            }

    fun calcPausedState(oldState: AppState): AppState.Paused =
            when (oldState) {
                is AppState.Paused -> oldState
                is AppState.Running -> {
                    val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                    AppState.Paused(elapsedTime = elapsedTime)
                }
            }
}