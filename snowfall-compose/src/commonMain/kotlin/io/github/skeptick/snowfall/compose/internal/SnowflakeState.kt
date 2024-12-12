package io.github.skeptick.snowfall.compose.internal

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import kotlin.random.Random

@Stable
internal class SnowflakeState(
    x: Float,
    y: Float,
    alphaOffset: Float,
    angle: Float,
    scale: Float,
    speed: Float,
    var scaleRatio: Float,
    var speedRatio: Float
) {
    var x by mutableFloatStateOf(x)
    var y by mutableFloatStateOf(y)
    var alpha by mutableFloatStateOf(1f)
    var alphaOffset by mutableFloatStateOf(alphaOffset)
    var angle by mutableFloatStateOf(angle)
    var scale by mutableFloatStateOf(scale)
    var speed by mutableFloatStateOf(speed)
}

internal fun SnowflakeState(
    canvasSize: Size,
    pathSize: Float,
    size: ClosedRange<Float>,
    speed: ClosedRange<Float>,
): SnowflakeState {
    val scaleRatio = Random.nextFloat()
    val speedRatio = Random.nextFloat()
    return SnowflakeState(
        x = Random.nextFloat() * canvasSize.width,
        y = Random.nextFloat() * canvasSize.height * -1f,
        alphaOffset = Random.nextFloat() * 0.5f,
        angle = Random.nextFloat() * SourceAngleRange,
        scale = scaleRatio * size / pathSize,
        speed = speedRatio * speed,
        scaleRatio = scaleRatio,
        speedRatio = speedRatio
    )
}