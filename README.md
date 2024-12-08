# snowfall-compose

Very simple snowfall animation modifier for Compose Multiplatform.
  
![Screenshot](content/screenshot.png)

## :arrow_forward: [Live Demo WasmJS](https://skeptick.github.io/snowfall-compose/wasm/) / [Live Demo JS](https://skeptick.github.io/snowfall-compose/js/)

## Setup

```kotlin
kotlin {
    commonMain {
        dependencies {
            implementation("io.github.skeptick.snowfall:snowfall-compose:1.0.1")
        }
    }
}
```

## Usage

```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .snowfall()
)
```

```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .snowfall(
            color = Color.White,
            alpha = 0.3f,
            strokeWidth = 1f,
            drawPosition = SnowfallDrawPosition.Ahead,
            snowflakeMinSize = 10.dp,
            snowflakeMaxSize = 20.dp,
            snowflakeMinSpeed = 0.5f,
            snowflakeMaxSpeed = 1.5f,
            snowflakeDensity = 1f
        )
) {
    // content
}
```