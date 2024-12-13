package io.github.skeptick.snowfall.compose.internal

import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.util.fastForEach

@Stable
internal class SnowfallState(
    var snowflakes: List<SnowflakeState> = emptyList()
) {
    companion object StateSaver : Saver<SnowfallState, Any> by listSaver(
        save = { state ->
            buildList(state.snowflakes.size * 8) {
                state.snowflakes.fastForEach { snowflake ->
                    add(snowflake.x)
                    add(snowflake.y)
                    add(snowflake.alphaOffset)
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
                snowflakes = List((list.size) / 8) { index ->
                    SnowflakeState(
                        x = list[index * 7 + 0],
                        y = list[index * 7 + 1],
                        alphaOffset = list[index * 7 + 2],
                        angle = list[index * 7 + 3],
                        scale = list[index * 7 + 4],
                        speed = list[index * 7 + 5],
                        scaleRatio = list[index * 7 + 6],
                        speedRatio = list[index * 7 + 7],
                    )
                }
            )
        }
    )
}