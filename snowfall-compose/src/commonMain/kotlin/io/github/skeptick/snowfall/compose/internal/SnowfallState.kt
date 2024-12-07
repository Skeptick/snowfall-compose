package io.github.skeptick.snowfall.compose.internal

import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.util.fastForEach

@Stable
internal class SnowfallState(
    var canvasSize: Size = Size.Zero,
    var snowflakes: List<SnowflakeState> = emptyList()
) {
    companion object StateSaver : Saver<SnowfallState, Any> by listSaver(
        save = { state ->
            buildList(2 + state.snowflakes.size * 7) {
                add(state.canvasSize.width)
                add(state.canvasSize.height)
                state.snowflakes.fastForEach { snowflake ->
                    add(snowflake.x)
                    add(snowflake.y)
                    add(snowflake.angle)
                    add(snowflake.scale)
                    add(snowflake.speed)
                    add(snowflake.scaleRatio)
                    add(snowflake.speedRatio)
                }
            }
        },
        restore = { list: List<Float> ->
            SnowfallState(
                canvasSize = Size(list[0], list[1]),
                snowflakes = List((list.size - 2) / 7) { index ->
                    SnowflakeState(
                        x = list[index * 7 + 2],
                        y = list[index * 7 + 3],
                        angle = list[index * 7 + 4],
                        scale = list[index * 7 + 5],
                        speed = list[index * 7 + 6],
                        scaleRatio = list[index * 7 + 7],
                        speedRatio = list[index * 7 + 8],
                    )
                }
            )
        }
    )
}