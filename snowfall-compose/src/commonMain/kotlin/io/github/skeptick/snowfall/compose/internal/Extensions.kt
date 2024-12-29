package io.github.skeptick.snowfall.compose.internal

import androidx.compose.ui.graphics.Path

internal val SourceAngleRange = 1.45f..1.55f
internal val AlphaOffsetRange = 0.5f..1f
internal val AddableAngleRange = -0.0025f..0.0025f

internal operator fun Float.times(range: ClosedRange<Float>) =
    this * (range.endInclusive - range.start) + range.start

internal val List<Path>.pathSizes: FloatArray
    get() = FloatArray(size) { i -> this[i].getBounds().size.maxDimension }