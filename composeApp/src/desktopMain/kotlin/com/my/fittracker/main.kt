package com.my.fittracker

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.my.fittracker.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "FitTracker",
    ) {
        App()
    }
}