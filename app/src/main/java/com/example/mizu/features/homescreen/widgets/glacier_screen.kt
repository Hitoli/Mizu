package com.example.mizu.features.homescreen.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.waterColor

@Composable
fun GlacierScreen(
    modifier: Modifier = Modifier,
    onWaterTrackingResourceAmount: Int,
    onTotalWaterTrackingResourceAmount: Int,
    screenWidth: Dp,
    screenHeight: Dp
) {

    val waterPercentageFilled = animateFloatAsState(
        targetValue = onWaterTrackingResourceAmount.toFloat() / onTotalWaterTrackingResourceAmount.toFloat(),
        label = "water",
        animationSpec = tween(durationMillis = 1000)
    )
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .size(width = screenWidth.value.dp, height = screenHeight.value.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "2500ml",
                modifier = Modifier.offset(
                    x = screenWidth.value * 0.18.dp,
                    y = screenWidth.value * 0.08.dp,
                ),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF29302C),
                    textAlign = TextAlign.Start,
                )
            )
            Glacier(
                screenHeight = screenHeight,
                screenWidth =screenWidth,
                waterPercentageFilled = waterPercentageFilled
            )

            Text(
                text = "0ml",
                modifier = Modifier.offset(
                    x = screenWidth.value * 0.09.dp,
                    y = -screenWidth.value * 0.09.dp,
                ),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF29302C),
                    textAlign = TextAlign.Start,
                )
            )
        }


    }
}

@Composable
fun Glacier(
    screenWidth: Dp, screenHeight: Dp, waterPercentageFilled: State<Float>
) {
    Canvas(modifier = Modifier
        .width(
            screenWidth.value.dp
        )
        .height(screenHeight.value.dp)) {
        val width = size.width - 100f;
        val height = size.height;
        val waterWavesYPosition = (1 - waterPercentageFilled.value) * width
        var waterPath = Path().apply {
            moveTo(
                x = width / 2,
                y = 0f
            )
            lineTo(
                x = width / 2 + 25f,
                y = 50f
            )
            lineTo(
                x = width / 2 + 150f,
                y = 100f
            )
            lineTo(
                x = width / 2 + 250f,
                y = 230f
            )
            lineTo(
                x = width / 2 + 320f,
                y = 250f
            )

            lineTo(
                x = width / 2 + 400f,
                y = 310f
            )
            lineTo(
                x = width / 2 + 400f,
                y = 430f
            )
            lineTo(
                x = width / 2 + 300f,
                y = 800f
            )
            lineTo(
                x = width / 2 + 240f,
                y = 850f
            )
            lineTo(
                x = width / 2 + 180f,
                y = 1000f
            )
            lineTo(
                x = width / 2 + 100f,
                y = 1090f
            )
            lineTo(
                x = width / 2 - 100f,
                y = 1000f
            )
            lineTo(
                x = width / 2 - 300f,
                y = 500f
            )
            lineTo(
                x = width / 2 - 300f,
                y = 400f
            )
            lineTo(
                x = width / 2 - 200f,
                y = 200f
            )
            lineTo(
                x = width / 2 - 150f,
                y = 180f
            )
            lineTo(
                x = width / 2 - 120f,
                y = 100f
            )
            lineTo(
                x = width / 2 - 100f,
                y = 50f
            )
            lineTo(
                x = width / 2 - 20f,
                y = 30f
            )
            close()
        }
        drawPath(waterPath, Color.Black, style = Stroke(width = 0.8f))
        clipPath(
            waterPath
        ) {
            drawRect(
                color = Color(0XFFEFEFEF), size = size
            )
            var waterFillPath = Path().apply {
                moveTo(
                    x = 0f,
                    y = waterWavesYPosition
                )
                lineTo(
                    x = size.width,
                    y = waterWavesYPosition
                )
                lineTo(
                    x = size.width,
                    y = size.height
                )
                lineTo(
                    x = 0f,
                    y = size.height
                )
                close()

            }
            drawPath(waterFillPath, waterColor)

        }
//            drawPath(waterPath, Color.Black, style = Stroke(width=1f))
//            drawPath(waterPath, waterColor)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGlacierScreen() {
    val LocalConfig = LocalConfiguration.current
    val screenWidth = LocalConfig.screenWidthDp.dp
    val screenHeight = LocalConfig.screenHeightDp.dp
    GlacierScreen(
        onWaterTrackingResourceAmount = 499,

        onTotalWaterTrackingResourceAmount = 1200,
       modifier = Modifier, screenWidth = screenWidth.value.dp, screenHeight = 450.dp
    )
}