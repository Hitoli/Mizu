package com.example.mizu.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.blackShadeButtonColor
import com.example.mizu.ui.theme.buttonTextColor
import com.example.mizu.ui.theme.circleWaterIndicatorColor
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.waterColor

@Composable
fun HomeScreen(
    onPad: PaddingValues,
    onWaterTrackingResourceAmount: Int,
    getWaterTrackingResourceAmount: (Int) -> Unit,
    onTotalWaterTrackingResourceAmount: Int,
    getAddWater: () -> Unit,
    onUserName: String,
    modifier: Modifier = Modifier
) {
    val LocalConfig = LocalConfiguration.current
    val screenWidth = LocalConfig.screenWidthDp.dp
    val screenHeight = LocalConfig.screenHeightDp.dp
    val waterPercentageFilled = animateFloatAsState(
        targetValue = onWaterTrackingResourceAmount.toFloat() / onTotalWaterTrackingResourceAmount.toFloat(),
        label = "water",
        animationSpec = tween(durationMillis = 1000)
    )

    Box(modifier = modifier) {

        Box(modifier = Modifier
            .height(800.dp)
            .width(400.dp)
            .align(Alignment.Center)) {
                Text(
                    text = "Hi, Hitesh",
                    modifier=Modifier.padding(top = 50.dp, start = 24.dp),
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF29302C),
                        textAlign = TextAlign.Center,
                    )
                )
            Text(
                text = "2500ml",
                modifier=Modifier.offset( x =screenWidth  / 2,
                    y =100.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF29302C),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "0ml",
                modifier=Modifier.offset( x =screenWidth  / 2+75.dp,
                    y =675.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF29302C),
                    textAlign = TextAlign.Center,
                )
            )
            Button(
                onClick = { getAddWater() },
                modifier = Modifier
                    .width(212.dp)
                    .height(50.dp).align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(blackShadeButtonColor),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "250 ml",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        color = buttonTextColor,

                        textAlign = TextAlign.Center,
                    )
                )
            }

            Box(modifier = Modifier.align(Alignment.TopEnd)){
                Canvas(modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(16.dp)
                    ){
                    drawCircle(color = circleWaterIndicatorColor )
                }
                Text(
                    text = "1500ml",
                    modifier=Modifier.align(Alignment.Center),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFE5E5),

                        textAlign = TextAlign.Center,
                    )
                )
            }


        }
        Canvas(modifier = Modifier
            .width(
                500.dp
            )
            .height(650.dp)
            .align(Alignment.Center)) {
            val width = size.width - 100f;
            val height = size.height;
            val waterWavesYPosition = (1 - waterPercentageFilled.value) * width
            var waterPath = Path().apply {
                moveTo(
                    x = width / 2,
                    y = 100f
                )
                lineTo(
                    x = width / 2 + 50f,
                    y = 200f
                )
                lineTo(
                    x = width / 2 + 250f,
                    y = 350f
                )
                lineTo(
                    x = width / 2 + 350f,
                    y = 480f
                )
                lineTo(
                    x = width / 2 + 420f,
                    y = 440f
                )

                lineTo(
                    x = width / 2 + 500f,
                    y = 460f
                )
                lineTo(
                    x = width / 2 + 540f,
                    y = 580f
                )
                lineTo(
                    x = width / 2 + 450f,
                    y = 1000f
                )
                lineTo(
                    x = width / 2 + 400f,
                    y = 1050f
                )
                lineTo(
                    x = width / 2 + 370f,
                    y = 1500f
                )
                lineTo(
                    x = width / 2 + 200f,
                    y = 1690f
                )
                lineTo(
                    x = width / 2 - 200f,
                    y = 1400f
                )
                lineTo(
                    x = width / 2 - 450f,
                    y = 800f
                )
                lineTo(
                    x = width / 2 - 450f,
                    y = 600f
                )
                lineTo(
                    x = width / 2 - 300f,
                    y = 400f
                )
                lineTo(
                    x = width / 2 - 250f,
                    y = 380f
                )
                lineTo(
                    x = width / 2 - 220f,
                    y = 250f
                )
                lineTo(
                    x = width / 2 - 200f,
                    y = 200f
                )
                lineTo(
                    x = width / 2 - 120f,
                    y = 180f
                )
                close()
            }
            drawPath(waterPath, Color.Black, style = Stroke(width = 0.8f))
            clipPath(
                waterPath
            ) {
                drawRect(
                    color = Color.Black.copy(alpha = 0.3f), size = size
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

}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        onPad = PaddingValues(20.dp),
        onWaterTrackingResourceAmount = 500,
        getWaterTrackingResourceAmount = {},
        onTotalWaterTrackingResourceAmount = 1200,
        getAddWater = {},
        onUserName = "Hitesh",
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(backgroundColor1, backgroundColor2)
                )
            )
    )
}