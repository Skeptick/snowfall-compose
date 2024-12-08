package io.github.skeptick.snowfall.sample

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun SliderSelector(
    title: String,
    selectedValue: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit,
    valueFormatter: (Float) -> Float = { it }
) {
    SelectorLayout(
        title = title,
        value = format(valueFormatter(selectedValue))
    ) {
        Slider(
            modifier = Modifier.padding(16.dp),
            value = selectedValue,
            valueRange = valueRange,
            onValueChange = onValueChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RangeSliderSelector(
    title: String,
    activeRangeStart: Float,
    activeRangeEnd: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float, Float) -> Unit,
    valueFormatter: (Float) -> Float = { it }
) {
    SelectorLayout(
        title = title,
        value = format(valueFormatter(activeRangeStart)..valueFormatter(activeRangeEnd))
    ) {
        val state = remember {
            RangeSliderState(
                activeRangeStart = activeRangeStart,
                activeRangeEnd =activeRangeEnd,
                valueRange = valueRange
            )
        }
        RangeSlider(
            modifier = Modifier.padding(16.dp),
            state = state
        )
        LaunchedEffect(state.activeRangeStart, state.activeRangeEnd) {
            onValueChange(state.activeRangeStart, state.activeRangeEnd)
        }
    }
}

@Composable
internal fun SelectorLayout(
    title: String,
    value: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        SelectorHeader(title, value)
        content()
    }
}

@Composable
private fun SelectorHeader(
    title: String,
    value: String? = null,
) {
    Row {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        if (value != null) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

private fun format(float: Float): String {
    val integerPart = float.toString().substringBefore('.')
    val decimalPart = float.toString().substringAfter('.', "").take(2).padEnd(2, padChar = '0')
    return "$integerPart.$decimalPart"
}

private fun format(range: ClosedRange<Float>): String {
    return "${format(range.start)} .. ${format(range.endInclusive)}"
}