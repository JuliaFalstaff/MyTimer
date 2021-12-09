package com.example.timer.model

sealed class AppState {
    data class Paused(val elapsedTime: Long) : AppState()
    data class Running(val startTime: Long, val elapsedTime: Long) : AppState()
}
