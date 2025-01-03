package io.github.skeptick.snowfall.compose.internal

import androidx.compose.ui.graphics.Path

internal val DefaultSnowflakes by lazy(LazyThreadSafetyMode.NONE) {
    listOf(
        Snowflake1,
        Snowflake2,
        Snowflake3
    )
}

private val Snowflake1 by lazy(LazyThreadSafetyMode.NONE) {
    Path().apply {
        moveTo(11.997f, 2f)
        lineTo(11.997f, 13.04f)
        moveTo(12.002f, 6.376f)
        lineTo(15.249f, 3.129f)
        moveTo(12.002f, 9.161f)
        lineTo(15.249f, 5.914f)
        moveTo(8.702f, 3.144f)
        lineTo(11.948f, 6.39f)
        moveTo(8.702f, 5.929f)
        lineTo(11.948f, 9.175f)
        moveTo(3.699f, 16.562f)
        lineTo(13.2f, 10.941f)
        moveTo(7.463f, 14.33f)
        lineTo(3.015f, 13.189f)
        moveTo(9.859f, 12.912f)
        lineTo(5.412f, 11.771f)
        moveTo(6.361f, 18.816f)
        lineTo(7.502f, 14.369f)
        moveTo(8.758f, 17.398f)
        lineTo(9.899f, 12.951f)
        moveTo(20.347f, 16.485f)
        lineTo(10.797f, 10.946f)
        moveTo(16.559f, 14.294f)
        lineTo(17.738f, 18.731f)
        moveTo(14.15f, 12.896f)
        lineTo(15.329f, 17.334f)
        moveTo(21.011f, 13.061f)
        lineTo(16.573f, 14.24f)
        moveTo(18.602f, 11.663f)
        lineTo(14.164f, 12.843f)
        moveTo(12.003f, 21.295f)
        lineTo(12.003f,10.255f)
        moveTo(11.998f, 16.919f)
        lineTo(8.751f, 20.165f)
        moveTo(11.998f, 14.134f)
        lineTo(8.751f, 17.381f)
        moveTo(15.298f, 20.151f)
        lineTo(12.052f, 16.904f)
        moveTo(15.298f, 17.366f)
        lineTo(12.052f, 14.12f)
        moveTo(20.301f, 6.732f)
        lineTo(10.8f, 12.354f)
        moveTo(16.538f, 8.965f)
        lineTo(20.985f, 10.106f)
        moveTo(14.141f, 10.383f)
        lineTo(18.588f, 11.524f)
        moveTo(17.639f, 4.478f)
        lineTo(16.498f, 8.926f)
        moveTo(15.242f, 5.897f)
        lineTo(14.101f, 10.344f)
        moveTo(3.654f, 6.809f)
        lineTo(13.203f, 12.348f)
        moveTo(7.441f, 9.001f)
        lineTo(6.262f, 4.563f)
        moveTo(9.85f, 10.398f)
        lineTo(8.671f, 5.961f)
        moveTo(2.989f, 10.234f)
        lineTo(7.427f, 9.054f)
        moveTo(5.398f, 11.631f)
        lineTo(9.836f, 10.452f)
    }
}

private val Snowflake2 by lazy(LazyThreadSafetyMode.NONE) {
    Path().apply {
        moveTo(12.102f, 3f)
        lineTo(12.102f, 12.255f)
        moveTo(12.102f, 3f)
        lineTo(12.102f, 12.255f)
        moveTo(9.623f, 3.855f)
        lineTo(11.896f, 5.168f)
        moveTo(14.377f, 3.855f)
        lineTo(12.104f, 5.168f)
        moveTo(12.102f, 21f)
        lineTo(12.102f, 11.745f)
        moveTo(12.102f, 21f)
        lineTo(12.102f, 11.745f)
        moveTo(9.623f, 20.145f)
        lineTo(11.896f, 18.832f)
        moveTo(14.377f, 20.145f)
        lineTo(12.104f, 18.832f)
        moveTo(3f, 11.898f)
        lineTo(12.255f, 11.898f)
        moveTo(3f, 11.898f)
        lineTo(12.255f, 11.898f)
        moveTo(3.855f, 14.377f)
        lineTo(5.168f, 12.104f)
        moveTo(3.855f, 9.623f)
        lineTo(5.168f, 11.896f)
        moveTo(21f, 11.898f)
        lineTo(11.745f, 11.898f)
        moveTo(21f, 11.898f)
        lineTo(11.745f, 11.898f)
        moveTo(20.145f, 14.377f)
        lineTo(18.832f, 12.104f)
        moveTo(20.145f, 9.623f)
        lineTo(18.832f, 11.896f)
        moveTo(18.436f, 5.709f)
        lineTo(11.892f, 12.253f)
        moveTo(18.436f, 5.709f)
        lineTo(11.892f, 12.253f)
        moveTo(16.078f, 4.56f)
        lineTo(16.758f, 7.096f)
        moveTo(19.44f, 7.922f)
        lineTo(16.904f, 7.242f)
        moveTo(5.709f, 18.436f)
        lineTo(12.253f, 11.892f)
        moveTo(5.709f, 18.436f)
        lineTo(12.253f, 11.892f)
        moveTo(4.56f, 16.078f)
        lineTo(7.096f, 16.758f)
        moveTo(7.922f, 19.44f)
        lineTo(7.242f, 16.904f)
        moveTo(5.709f, 5.564f)
        lineTo(12.253f, 12.108f)
        moveTo(5.709f, 5.564f)
        lineTo(12.253f, 12.108f)
        moveTo(4.56f, 7.922f)
        lineTo(7.096f, 7.242f)
        moveTo(7.922f, 4.56f)
        lineTo(7.242f, 7.096f)
        moveTo(18.436f, 18.291f)
        lineTo(11.892f, 11.747f)
        moveTo(18.436f, 18.291f)
        lineTo(11.892f, 11.747f)
        moveTo(16.078f, 19.44f)
        lineTo(16.758f, 16.904f)
        moveTo(19.44f, 16.078f)
        lineTo(16.904f, 16.758f)
    }
}

private val Snowflake3 by lazy(LazyThreadSafetyMode.NONE) {
    Path().apply {
        moveTo(12.003f, 3f)
        lineTo(12.003f, 12.633f)
        moveTo(14.469f, 3.865f)
        lineTo(12.017f, 8.112f)
        moveTo(12.003f, 3f)
        lineTo(12.003f, 12.633f)
        moveTo(11.986f, 7.984f)
        lineTo(9.18f, 4.007f)
        moveTo(14.469f, 3.865f)
        lineTo(12.017f, 8.112f)
        moveTo(19.701f, 7.545f)
        lineTo(11.573f, 12.715f)
        moveTo(15.445f, 10.177f)
        lineTo(17.383f, 5.712f)
        moveTo(20.294f, 10.089f)
        lineTo(15.394f, 10.3f)
        moveTo(4.091f, 7.545f)
        lineTo(12.219f, 12.715f)
        moveTo(8.318f, 10.197f)
        lineTo(6.443f, 5.705f)
        moveTo(3.497f, 10.089f)
        lineTo(8.397f, 10.3f)
        moveTo(19.825f, 17.255f)
        lineTo(11.502f, 12.405f)
        moveTo(15.541f, 14.63f)
        lineTo(20.403f, 14.405f)
        moveTo(17.836f, 18.95f)
        lineTo(15.401f, 14.693f)
        moveTo(4.225f, 17.255f)
        lineTo(12.547f, 12.405f)
        moveTo(8.509f, 14.633f)
        lineTo(3.647f, 14.402f)
        moveTo(6.213f, 18.95f)
        lineTo(8.648f, 14.693f)
        moveTo(12.003f, 21.648f)
        lineTo(12.003f, 12.016f)
        moveTo(11.99f, 16.666f)
        lineTo(9.176f, 20.637f)
        moveTo(14.469f, 20.784f)
        lineTo(12.017f, 16.536f)
        moveTo(12.003f, 21.648f)
        lineTo(12.003f, 12.016f)
        moveTo(14.469f, 20.784f)
        lineTo(12.017f, 16.536f)
    }
}