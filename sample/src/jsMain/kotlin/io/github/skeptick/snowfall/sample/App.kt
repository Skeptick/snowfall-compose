package io.github.skeptick.snowfall.sample

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow(
            title = "Snowfall Sample",
            canvasElementId = "content",
            applyDefaultStyles = true
        ) {
            RootView()
        }
    }
}