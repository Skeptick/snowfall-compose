package io.github.skeptick.snowfall.sample

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(
        title = "Snowfall Sample",
        canvasElementId = "content",
        applyDefaultStyles = true
    ) {
        RootView()
    }
}