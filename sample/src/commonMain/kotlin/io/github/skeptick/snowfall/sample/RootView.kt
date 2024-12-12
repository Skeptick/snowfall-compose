package io.github.skeptick.snowfall.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import io.github.skeptick.snowfall.compose.SnowfallDrawPosition
import io.github.skeptick.snowfall.compose.snowfall

@Composable
fun RootView() {
    var color by remember { mutableStateOf(Color.White) }
    var alpha by remember { mutableFloatStateOf(0.5f) }
    var fading by remember { mutableFloatStateOf(3f) }
    var strokeWidth by remember { mutableFloatStateOf(1f) }
    var drawPosition by remember { mutableStateOf(SnowfallDrawPosition.Ahead) }
    var snowflakeMinSize by remember { mutableStateOf(10.dp) }
    var snowflakeMaxSize by remember { mutableStateOf(20.dp) }
    var snowflakeMinSpeed by remember { mutableStateOf(0.2.dp) }
    var snowflakeMaxSpeed by remember { mutableStateOf(1.dp) }
    var snowflakeDensity by remember { mutableFloatStateOf(1f) }

    val preview = remember {
        movableContentOf { modifier: Modifier ->
            Preview(
                modifier = modifier,
                color = color,
                alpha = alpha,
                fading = fading,
                strokeWidth = strokeWidth,
                drawPosition = drawPosition,
                snowflakeMinSize = snowflakeMinSize,
                snowflakeMaxSize = snowflakeMaxSize,
                snowflakeMinSpeed = snowflakeMinSpeed,
                snowflakeMaxSpeed = snowflakeMaxSpeed,
                snowflakeDensity = snowflakeDensity
            )
        }
    }

    val settings = remember {
        movableContentOf { modifier: Modifier ->
            Settings(
                modifier = modifier,
                color = color,
                alpha = alpha,
                fading = fading,
                strokeWidth = strokeWidth,
                drawPosition = drawPosition,
                snowflakeMinSize = snowflakeMinSize,
                snowflakeMaxSize = snowflakeMaxSize,
                snowflakeMinSpeed = snowflakeMinSpeed,
                snowflakeMaxSpeed = snowflakeMaxSpeed,
                snowflakeDensity = snowflakeDensity,
                onColorChange = { color = it },
                onAlphaChange = { alpha = it },
                onFadingChange = { fading = it },
                onStrokeChange = { strokeWidth = it },
                onDrawPositionChange = { drawPosition = it },
                onSnowflakeSizeChange = { min, max ->
                    snowflakeMinSize = min
                    snowflakeMaxSize = max
                },
                onSnowflakeSpeedChange = { min, max ->
                    snowflakeMinSpeed = min
                    snowflakeMaxSpeed = max
                },
                onSnowflakeDensityChange = { snowflakeDensity = it }
            )
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Start + WindowInsetsSides.End + WindowInsetsSides.Bottom
                )
            )
    ) {
        val isCompact = maxWidth < 800.dp

        if (isCompact) {
            Column(modifier = Modifier.fillMaxSize()) {
                preview(Modifier.fillMaxWidth().weight(1f))
                settings(Modifier.fillMaxWidth().weight(1f))
            }
        } else {
            Row(modifier = Modifier.fillMaxSize()) {
                preview(Modifier.fillMaxHeight().weight(1f))
                settings(Modifier.fillMaxHeight().weight(1f))
            }
        }
    }
}

@Composable
private fun Preview(
    color: Color,
    alpha: Float,
    fading: Float,
    strokeWidth: Float,
    drawPosition: SnowfallDrawPosition,
    snowflakeMinSize: Dp,
    snowflakeMaxSize: Dp,
    snowflakeMinSpeed: Dp,
    snowflakeMaxSpeed: Dp,
    snowflakeDensity: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.Black)
            .snowfall(
                color = color,
                alpha = alpha,
                fading = fading,
                strokeWidth = strokeWidth,
                drawPosition = drawPosition,
                snowflakeMinSize = snowflakeMinSize,
                snowflakeMaxSize = snowflakeMaxSize,
                snowflakeMinSpeed = snowflakeMinSpeed,
                snowflakeMaxSpeed = snowflakeMaxSpeed,
                snowflakeDensity = snowflakeDensity
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Happy\nNew Year!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Settings(
    color: Color,
    alpha: Float,
    fading: Float,
    strokeWidth: Float,
    drawPosition: SnowfallDrawPosition,
    snowflakeMinSize: Dp,
    snowflakeMaxSize: Dp,
    snowflakeMinSpeed: Dp,
    snowflakeMaxSpeed: Dp,
    snowflakeDensity: Float,
    onColorChange: (Color) -> Unit,
    onAlphaChange: (Float) -> Unit,
    onFadingChange: (Float) -> Unit,
    onStrokeChange: (Float) -> Unit,
    onDrawPositionChange: (SnowfallDrawPosition) -> Unit,
    onSnowflakeSizeChange: (Dp, Dp) -> Unit,
    onSnowflakeSpeedChange: (Dp, Dp) -> Unit,
    onSnowflakeDensityChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        ColorSelector(
            selectedColor = color,
            onClick = onColorChange
        )

        SliderSelector(
            title = "Alpha",
            selectedValue = alpha,
            valueRange = 0f..1f,
            onValueChange = onAlphaChange
        )

        SliderSelector(
            title = "Fading",
            selectedValue = fading,
            valueRange = 0f..10f,
            onValueChange = onFadingChange
        )

        SliderSelector(
            title = "Stroke Width",
            selectedValue = strokeWidth,
            valueRange = 1f..3f,
            onValueChange = onStrokeChange
        )

        DrawPositionSelector(
            selectedPosition = drawPosition,
            onSelect = onDrawPositionChange
        )

        with(density) {
            RangeSliderSelector(
                title = "Snowflake Size",
                activeRangeStart = snowflakeMinSize.toPx(),
                activeRangeEnd = snowflakeMaxSize.toPx(),
                valueRange = 10.dp.toPx()..50.dp.toPx(),
                onValueChange = { min, max -> onSnowflakeSizeChange(min.toDp(), max.toDp()) },
                valueFormatter = { it.toDp().value }
            )
        }

        with(density) {
            RangeSliderSelector(
                title = "Snowflake Speed",
                activeRangeStart = snowflakeMinSpeed.toPx(),
                activeRangeEnd = snowflakeMaxSpeed.toPx(),
                valueRange = 0.dp.toPx()..5.dp.toPx(),
                onValueChange = { min, max -> onSnowflakeSpeedChange(min.toDp(), max.toDp()) },
                valueFormatter = { it.toDp().value }
            )
        }

        SliderSelector(
            title = "Snowflake Density",
            selectedValue = snowflakeDensity,
            valueRange = 0.1f..5f,
            onValueChange = onSnowflakeDensityChange
        )
    }
}

@Composable
private fun ColorSelector(
    selectedColor: Color,
    onClick: (Color) -> Unit
) {
    SelectorLayout(
        title = "Color"
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .horizontalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf(
                Color.White, Color.Red, Color.Green, Color.Blue,
                Color.Yellow, Color.Cyan, Color.Magenta
            ).fastForEach { color ->
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .border(
                            width = if (color == selectedColor) 2.dp else 1.dp,
                            color = if (color == selectedColor) Color.Blue else Color.Black
                        )
                        .background(color)
                        .clickable(onClick = { onClick(color) })
                )
            }
        }
    }
}

@Composable
private fun DrawPositionSelector(
    selectedPosition: SnowfallDrawPosition,
    onSelect: (SnowfallDrawPosition) -> Unit
) {
    SelectorLayout(
        title = "Draw Position"
    ) {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SegmentedButton(
                selected = selectedPosition == SnowfallDrawPosition.Ahead,
                onClick = { onSelect(SnowfallDrawPosition.Ahead) },
                shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
                label = { Text(text = "Ahead Content") }
            )

            SegmentedButton(
                selected = selectedPosition == SnowfallDrawPosition.Behind,
                onClick = { onSelect(SnowfallDrawPosition.Behind) },
                shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp),
                label = { Text(text = "Behind Content") }
            )
        }
    }
}