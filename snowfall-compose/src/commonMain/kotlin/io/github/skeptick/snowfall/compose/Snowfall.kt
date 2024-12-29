package io.github.skeptick.snowfall.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.LayoutAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.util.fastMapIndexedNotNull
import io.github.skeptick.snowfall.compose.internal.AddableAngleRange
import io.github.skeptick.snowfall.compose.internal.DefaultSnowflakes
import io.github.skeptick.snowfall.compose.internal.SnowfallState
import io.github.skeptick.snowfall.compose.internal.SnowflakeState
import io.github.skeptick.snowfall.compose.internal.SourceAngleRange
import io.github.skeptick.snowfall.compose.internal.pathSizes
import io.github.skeptick.snowfall.compose.internal.times
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.random.Random

public enum class SnowfallDrawPosition {
    Behind,
    Ahead
}

@Composable
public fun Modifier.snowfall(
    color: Color = Color.White,
    alpha: Float = 0.3f,
    fadeThreshold: Float = 1f,
    fadeThresholdSpread: Float = 0f,
    strokeWidth: Float = 1f,
    drawPosition: SnowfallDrawPosition = SnowfallDrawPosition.Ahead,
    snowflakes: List<Path> = DefaultSnowflakes,
    snowflakeMinSize: Dp = 10.dp,
    snowflakeMaxSize: Dp = 20.dp,
    snowflakeMinSpeed: Dp = 0.2.dp,
    snowflakeMaxSpeed: Dp = 1.dp,
    snowflakeDensity: Float = 1f
): Modifier =
    this then SnowfallElement(
        color = color,
        alpha = alpha,
        fadeThreshold = fadeThreshold,
        fadeThresholdSpread = fadeThresholdSpread,
        strokeWidth = strokeWidth,
        drawPosition = drawPosition,
        snowflakes = snowflakes,
        snowflakeMinSize = with(LocalDensity.current) { snowflakeMinSize.toPx() },
        snowflakeMaxSize = with(LocalDensity.current) { snowflakeMaxSize.toPx() },
        snowflakeMinSpeed = with(LocalDensity.current) { snowflakeMinSpeed.toPx() },
        snowflakeMaxSpeed = with(LocalDensity.current) { snowflakeMaxSpeed.toPx() },
        snowflakeDensity = 0.0003f / LocalDensity.current.density * snowflakeDensity,
        snowfallState = rememberSaveable(
            null,
            saver = SnowfallState.StateSaver,
            init = { SnowfallState() }
        )
    )

private data class SnowfallElement(
    val color: Color,
    val alpha: Float,
    val fadeThreshold: Float,
    val fadeThresholdSpread: Float,
    val strokeWidth: Float,
    val drawPosition: SnowfallDrawPosition,
    val snowflakes: List<Path>,
    val snowflakeMinSize: Float,
    val snowflakeMaxSize: Float,
    val snowflakeMinSpeed: Float,
    val snowflakeMaxSpeed: Float,
    val snowflakeDensity: Float,
    val snowfallState: SnowfallState
) : ModifierNodeElement<Snowfall>() {

    override fun create(): Snowfall {
        return Snowfall(
            color = color,
            alpha = alpha,
            fadeThreshold = fadeThreshold,
            fadeThresholdSpread = fadeThresholdSpread,
            stroke = Stroke(strokeWidth),
            drawPosition = drawPosition,
            snowflakes = ArrayList(snowflakes),
            snowflakeSize = snowflakeMinSize..snowflakeMaxSize,
            snowflakeSpeed = snowflakeMinSpeed..snowflakeMaxSpeed,
            snowflakeDensity = snowflakeDensity,
            snowfallState = snowfallState
        )
    }

    override fun update(node: Snowfall) {
        val countInvalidationRequired = node.snowflakeDensity != snowflakeDensity
        node.snowflakeDensity = snowflakeDensity
        if (countInvalidationRequired) node.updateCount()

        val sizeInvalidationRequired = sizeInvalidationRequired(node)
        val speedInvalidationRequired = speedInvalidationRequired(node)
        node.color = color
        node.alpha = alpha
        node.fadeThreshold = fadeThreshold
        node.fadeThresholdSpread = fadeThresholdSpread
        node.stroke = Stroke(strokeWidth)
        node.drawPosition = drawPosition
        node.snowflakes = ArrayList(snowflakes)
        node.snowflakeSize = snowflakeMinSize..snowflakeMaxSize
        node.snowflakeSpeed = snowflakeMinSpeed..snowflakeMaxSpeed
        node.snowfallState = snowfallState
        node.pathSizes = snowflakes.pathSizes
        if (sizeInvalidationRequired) node.updateSnowflakesSize()
        if (speedInvalidationRequired) node.updateSnowflakesSpeed()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "snowfall"
        properties["color"] = color
        properties["alpha"] = alpha
        properties["stroke_width"] = strokeWidth
        properties["draw_position"] = drawPosition
        properties["snowflakes_count"] = snowflakes.size
        properties["snowflake_min_size"] = snowflakeMinSize
        properties["snowflake_max_size"] = snowflakeMaxSize
        properties["snowflake_min_speed"] = snowflakeMinSpeed
        properties["snowflake_max_speed"] = snowflakeMaxSpeed
        properties["snowflake_density"] = snowflakeDensity
    }

    private fun sizeInvalidationRequired(node: Snowfall) =
        node.snowflakeSize.start != snowflakeMinSize
            || node.snowflakeSize.endInclusive != snowflakeMaxSize
            || node.snowflakes != snowflakes

    private fun speedInvalidationRequired(node: Snowfall) =
        node.snowflakeSpeed.start != snowflakeMinSpeed
            || node.snowflakeSpeed.endInclusive != snowflakeMaxSpeed

}

private class Snowfall(
    var color: Color,
    var alpha: Float,
    var fadeThreshold: Float,
    var fadeThresholdSpread: Float,
    var stroke: Stroke,
    var drawPosition: SnowfallDrawPosition,
    var snowflakes: List<Path>,
    var snowflakeSize: ClosedRange<Float>,
    var snowflakeSpeed: ClosedRange<Float>,
    var snowflakeDensity: Float,
    var snowfallState: SnowfallState,
    var pathSizes: FloatArray = snowflakes.pathSizes
) : DrawModifierNode, LayoutAwareModifierNode, Modifier.Node() {

    private var canvasSize = Size.Zero

    override fun onAttach() {
        coroutineScope.launch {
            while (isAttached) {
                withFrameNanos {
                    snowfallState.snowflakes.fastForEachIndexed { index, flake ->
                        flake.moveDown(index)
                    }
                }
            }
        }
    }

    override fun onRemeasured(size: IntSize) {
        if (canvasSize == size.toSize()) return
        canvasSize = size.toSize()
        updateCount()
    }

    override fun ContentDrawScope.draw() {
        if (drawPosition == SnowfallDrawPosition.Ahead) drawContent()

        clipRect {
            snowfallState.snowflakes.fastForEachIndexed { index, flake ->
                val scale = flake.scale
                val path = snowflakes[index % snowflakes.size]
                val pathSize = pathSizes[index % pathSizes.size]
                val flakeX = flake.x - (pathSize * scale) / 2
                val flakeY = flake.y - (pathSize * scale) / 2
                val offset = Offset(flakeX, flakeY)

                withTransform({
                    scale(scale, offset)
                    translate(offset.x, offset.y)
                }) {
                    drawPath(path, color, (alpha * flake.alpha), stroke)
                }
            }
        }

        if (drawPosition == SnowfallDrawPosition.Behind) drawContent()
    }

    fun updateCount() {
        if (canvasSize == Size.Zero) return
        val canvasArea = canvasSize.width * canvasSize.height
        val snowflakesCount = (canvasArea * snowflakeDensity).roundToInt()
        if (snowfallState.snowflakes.isEmpty()) {
            snowfallState.snowflakes = buildSnowflakes(snowflakesCount)
        } else {
            adjustCount(requiredCount = snowflakesCount)
        }
    }

    fun updateSnowflakesSize() {
        snowfallState.snowflakes.fastForEachIndexed { index, snowflake ->
            snowflake.scale = snowflake.scaleRatio * snowflakeSize / pathSizes[index % pathSizes.size]
        }
    }

    fun updateSnowflakesSpeed() {
        snowfallState.snowflakes.fastForEach { snowflake ->
            snowflake.speed = snowflake.speedRatio * snowflakeSpeed
        }
    }

    private fun adjustCount(requiredCount: Int) {
        val boundedSnowflakes = snowfallState.snowflakes.fastMapIndexedNotNull { index, flake ->
            val flakeSize = flake.scale * pathSizes[index % pathSizes.size]
            if (flake.x <= canvasSize.width + flakeSize && flake.y <= canvasSize.height + flakeSize) flake else null
        }
        if (boundedSnowflakes.size > requiredCount) {
            snowfallState.snowflakes = boundedSnowflakes.take(requiredCount)
        } else {
            val appendCount = requiredCount - boundedSnowflakes.size
            snowfallState.snowflakes = boundedSnowflakes + buildSnowflakes(appendCount, offset = boundedSnowflakes.size)
        }
    }

    private fun SnowflakeState.moveDown(index: Int) {
        val flakeSize = scale * pathSizes[index % pathSizes.size]
        x = (x + speed * cos(angle)).coerceIn(-flakeSize, canvasSize.width + flakeSize)
        y = (y + speed * sin(angle)).coerceIn(-canvasSize.height, canvasSize.height + flakeSize)
        angle += Random.nextFloat() * AddableAngleRange

        val yPos = (y / canvasSize.height)

        alpha = when {
            yPos < fadeThreshold  -> 1f
            yPos > (fadeThreshold + fadeThresholdSpread) -> 0f
            else -> (1 - (yPos - fadeThreshold) / (fadeThresholdSpread * alphaOffset)).coerceIn(0f, 1f)
        }
        if (y == canvasSize.height + flakeSize) recycle(index)
    }

    private fun SnowflakeState.recycle(index: Int) {
        val pathSize = pathSizes[index % pathSizes.size]
        scaleRatio = Random.nextFloat()
        speedRatio = Random.nextFloat()
        angle = Random.nextFloat() * SourceAngleRange
        alpha = 1f
        scale = scaleRatio * snowflakeSize / pathSize
        speed = speedRatio * snowflakeSpeed
        x = Random.nextFloat() * canvasSize.width
        y = -(scale * pathSize)
    }

    private fun buildSnowflakes(count: Int, offset: Int = 0): List<SnowflakeState> =
        List(count) { index ->
            SnowflakeState(
                canvasSize = canvasSize,
                pathSize = pathSizes[(index + offset) % pathSizes.size],
                size = snowflakeSize,
                speed = snowflakeSpeed
            )
        }

}