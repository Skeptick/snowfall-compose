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
    angle: Float,
    scale: Float,
    speed: Float,
    val scaleRatio: Float,
    val speedRatio: Float
) {
    var x by mutableFloatStateOf(x)
    var y by mutableFloatStateOf(y)
    var angle by mutableFloatStateOf(angle)
    var scale by mutableFloatStateOf(scale)
    var speed by mutableFloatStateOf(speed)
}

internal fun SnowflakeState(
    canvasSize: Size,
    pathSize: Float,
    size: ClosedRange<Float>,
    speed: ClosedRange<Float>
): SnowflakeState {
    val scaleRatio = Random.nextFloat()
    val speedRatio = Random.nextFloat()
    return SnowflakeState(
        x = Random.nextFloat() * canvasSize.width,
        y = Random.nextFloat() * canvasSize.height * -1f,
        angle = Random.nextFloat() * SourceAngleRange,
        scale = scaleRatio * size / pathSize,
        speed = speedRatio * speed,
        scaleRatio = scaleRatio,
        speedRatio = speedRatio
    )
}