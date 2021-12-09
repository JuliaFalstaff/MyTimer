package com.example.timer.usecase

import com.example.timer.model.AppState
import com.example.timer.model.TimestampProvider

class ElapsedTimeCalculator(private val timestampProvider: TimestampProvider) {
    fun calculate(state: AppState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}