package com.fahmy.task.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    private val isDark = mutableStateOf(true)

    fun toggleTheme() {
        this.isDark.value = !this.isDark.value
    }

    fun isDark(): Boolean = this.isDark.value
}