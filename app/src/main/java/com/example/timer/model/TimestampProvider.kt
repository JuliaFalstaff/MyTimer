package com.example.timer.model

interface TimestampProvider {
    fun getMilliseconds(): Long
}