package com.example.timer.di

import com.example.timer.usecase.ElapsedTimeCalculator
import com.example.timer.usecase.StopwatchStateCalculator
import com.example.timer.usecase.StopwatchStateHolder
import com.example.timer.utils.TimesMillisecondsFormatter
import com.example.timer.viewmodel.MainViewModel
import com.example.timer.viewmodel.StopWatchOrchestrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreen = module {
    factory { StopWatchOrchestrator(stopwatchStateHolder = get(), scope = get()) }
    factory { StopwatchStateCalculator(timestampProvider = get(), elapsedTimeCalculator = get()) }
    factory { ElapsedTimeCalculator(timestampProvider = get()) }
    factory { StopwatchStateHolder(stopwatchStateCalculator = get(), elapsedTimeCalculator = get(), timesmillisecFormatted = get()) }
    viewModel { MainViewModel() }
    factory { TimesMillisecondsFormatter() }
    factory { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
}