package io.github.skeptick.snowfall.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    var strokeWidth by remember { mutableFloatStateOf(1f) }
    var drawPosition by remember { mutableStateOf(SnowfallDrawPosition.Ahead) }
    var snowflakeMinSize by remember { mutableStateOf(10.dp) }
    var snowflakeMaxSize by remember { mutableStateOf(20.dp) }
    var snowflakeMinSpeed by remember { mutableStateOf(0.5f) }
    var snowflakeMaxSpeed by remember { mutableStateOf(1.5f) }
    var snowflakeDensity by remember { mutableFloatStateOf(1f) }

    val preview = remember {
        movableContentOf { modifier: Modifier ->
            Preview(
                modifier = modifier,
                color = color,
                alpha = alpha,
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
                strokeWidth = strokeWidth,
                drawPosition = drawPosition,
                snowflakeMinSize = snowflakeMinSize,
                snowflakeMaxSize = snowflakeMaxSize,
                snowflakeMinSpeed = snowflakeMinSpeed,
                snowflakeMaxSpeed = snowflakeMaxSpeed,
                snowflakeDensity = snowflakeDensity,
                onColorChange = { color = it },
                onAlphaChange = { alpha = it },
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
                preview(
                    Modifier.fillMaxWidth().weight(1f)
                )
                settings(
                    Modifier.fillMaxWidth().weight(1f)
                )
            }
        } else {
            Row(modifier = Modifier.fillMaxSize()) {
                preview(
                    Modifier.fillMaxHeight().weight(1f)
                )
                settings(
                    Modifier.fillMaxHeight().weight(1f)
                )
            }
        }
    }
}

@Composable
private fun Preview(
    color: Color,
    alpha: Float,
    strokeWidth: Float,
    drawPosition: SnowfallDrawPosition,
    snowflakeMinSize: Dp,
    snowflakeMaxSize: Dp,
    snowflakeMinSpeed: Float,
    snowflakeMaxSpeed: Float,
    snowflakeDensity: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.Black)
            .snowfall(
                color = color,
                alpha = alpha,
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
    strokeWidth: Float,
    drawPosition: SnowfallDrawPosition,
    snowflakeMinSize: Dp,
    snowflakeMaxSize: Dp,
    snowflakeMinSpeed: Float,
    snowflakeMaxSpeed: Float,
    snowflakeDensity: Float,
    onColorChange: (Color) -> Unit,
    onAlphaChange: (Float) -> Unit,
    onStrokeChange: (Float) -> Unit,
    onDrawPositionChange: (SnowfallDrawPosition) -> Unit,
    onSnowflakeSizeChange: (Dp, Dp) -> Unit,
    onSnowflakeSpeedChange: (Float, Float) -> Unit,
    onSnowflakeDensityChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        ColorSelector(
            selectedColor = color,
            onClick = onColorChange
        )

        AlphaSelector(
            selectedAlpha = alpha,
            onSelect = onAlphaChange
        )

        StrokeWidthSelector(
            selectedStroke = strokeWidth,
            onSelect = onStrokeChange
        )

        DrawPositionSelector(
            selectedPosition = drawPosition,
            onSelect = onDrawPositionChange
        )

        SizeSelector(
            selectedMinSize = snowflakeMinSize,
            selectedMaxSize = snowflakeMaxSize,
            onSelect = onSnowflakeSizeChange
        )

        SpeedSelector(
            selectedMinSpeed = snowflakeMinSpeed,
            selectedMaxSpeed = snowflakeMaxSpeed,
            onSelect = onSnowflakeSpeedChange
        )

        DensitySelector(
            selectedDensity = snowflakeDensity,
            onSelect = onSnowflakeDensityChange
        )
    }
}

@Composable
private fun ColorSelector(
    selectedColor: Color,
    onClick: (Color) -> Unit
) {
    Selector(
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
private fun AlphaSelector(
    selectedAlpha: Float,
    onSelect: (Float) -> Unit
) {
    Selector(
        title = "Alpha"
    ) {
        Slider(
            modifier = Modifier.padding(16.dp),
            value = selectedAlpha,
            valueRange = 0f..1f,
            onValueChange = { onSelect(it) }
        )
    }
}

@Composable
private fun StrokeWidthSelector(
    selectedStroke: Float,
    onSelect: (Float) -> Unit
) {
    Selector(
        title = "Stroke Width"
    ) {
        Slider(
            modifier = Modifier.padding(16.dp),
            value = selectedStroke * 10,
            valueRange = 10f..30f,
            onValueChange = { onSelect(it / 10) }
        )
    }
}

@Composable
private fun DrawPositionSelector(
    selectedPosition: SnowfallDrawPosition,
    onSelect: (SnowfallDrawPosition) -> Unit
) {
    Selector(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SizeSelector(
    selectedMinSize: Dp,
    selectedMaxSize: Dp,
    onSelect: (Dp, Dp) -> Unit
) {
    Selector(
        title = "Snowflake Size"
    ) {
        val density = LocalDensity.current
        val state = remember {
            RangeSliderState(
                activeRangeStart = with(density) { selectedMinSize.toPx() },
                activeRangeEnd = with(density) { selectedMaxSize.toPx() },
                valueRange = with(density) { (10.dp.toPx())..(50.dp.toPx()) }
            )
        }
        RangeSlider(
            modifier = Modifier.padding(16.dp),
            state = state
        )
        LaunchedEffect(state.activeRangeStart, state.activeRangeEnd) {
            onSelect(
                with(density) { state.activeRangeStart.toDp() },
                with(density) { state.activeRangeEnd.toDp() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpeedSelector(
    selectedMinSpeed: Float,
    selectedMaxSpeed: Float,
    onSelect: (Float, Float) -> Unit
) {
    Selector(
        title = "Snowflake Speed"
    ) {
        val state = remember {
            RangeSliderState(
                activeRangeStart = selectedMinSpeed,
                activeRangeEnd = selectedMaxSpeed,
                valueRange = 0f..5f
            )
        }
        RangeSlider(
            modifier = Modifier.padding(16.dp),
            state = state
        )
        LaunchedEffect(state.activeRangeStart, state.activeRangeEnd) {
            onSelect(
                state.activeRangeStart,
                state.activeRangeEnd
            )
        }
    }
}

@Composable
private fun DensitySelector(
    selectedDensity: Float,
    onSelect: (Float) -> Unit
) {
    Selector(
        title = "Snowflake Density"
    ) {
        Slider(
            modifier = Modifier.padding(16.dp),
            value = selectedDensity * 10,
            valueRange = 1f..20f,
            onValueChange = { onSelect(it / 10) }
        )
    }
}

@Composable
private fun Selector(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        content()
    }
}