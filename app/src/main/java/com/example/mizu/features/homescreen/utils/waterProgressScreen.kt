package com.example.mizu.features.homescreen.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.waterColor

@Composable
fun WaterProgressScreen(modifier: Modifier = Modifier,
                        onWaterTrackingResourceAmount: Int,
                        onWaterMeterResourceAmount: Int,
                        onTotalWaterTrackingResourceAmount: Int) {

    val LocalConfig = LocalConfiguration.current
    val screenWidth = LocalConfig.screenWidthDp.dp
    val screenHeight = LocalConfig.screenHeightDp.dp

    val waterPercentageFilled = animateFloatAsState(
        targetValue = onWaterTrackingResourceAmount.toFloat() / onTotalWaterTrackingResourceAmount.toFloat().coerceAtLeast(1f),
        label = "water",
        animationSpec = tween(durationMillis = 1000)
    )
    Box(modifier = modifier){
        Canvas(
            modifier = Modifier
                .width(screenWidth.value * 0.5.dp)
                .height(screenHeight.value * 0.05.dp)
                .clip(RoundedCornerShape(40.dp))
                .border(width = 0.5.dp, color = mizuBlack, shape = RoundedCornerShape(40.dp))
                .align(Alignment.TopStart)
        ) {
            val width = size.width
            val height = size.height
            val waterWavesYPosition = (waterPercentageFilled.value) * width
            val waterPath = Path().apply {
                moveTo(
                    x = 0f,
                    y = 0f
                )
                lineTo(
                    x = waterWavesYPosition,
                    y = 0f
                )
                lineTo(
                    x = waterWavesYPosition,
                    y = height
                )
                lineTo(
                    x = 0f,
                    y = height
                )
                close()
            }

            drawPath(waterPath, waterColor)


        }
        Text(
            text = "${onWaterMeterResourceAmount}%",
            modifier = Modifier
                .width(60.dp)
                .align(Alignment.CenterStart)
                .padding(start = 10.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight(500),
                color = mizuBlack,
                textAlign = TextAlign.Center,
            )
        )


    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewWaterProgressScreen() {
    WaterProgressScreen(onWaterTrackingResourceAmount = 800,
        onWaterMeterResourceAmount = 800*100/1200,
        onTotalWaterTrackingResourceAmount = 1200,
        modifier = Modifier)
}