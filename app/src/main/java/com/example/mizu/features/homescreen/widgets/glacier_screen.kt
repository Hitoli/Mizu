package com.example.mizu.features.homescreen.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
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
    Column(modifier = modifier.size(screenWidth,screenHeight), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.water_svg),
                contentDescription = "weekly summary",
                tint = mizuBlack
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Target : ${onTotalWaterTrackingResourceAmount} ml",
                modifier = Modifier,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,

                    textAlign = TextAlign.Center,
                )
            )

        }
        Spacer(modifier = Modifier.height(12.dp))

            Glacier(
                screenHeight = screenHeight,
                screenWidth =screenWidth,
                waterPercentageFilled = waterPercentageFilled, modifier = Modifier
                    .aspectRatio(0.8f)


            )






    }
}

@Composable
fun Indicator(
    screenWidth: Dp, screenHeight: Dp, waterPercentageFilled: State<Float>
) {
    Canvas(modifier = Modifier
        .width(
            screenWidth.value.dp
        )
        .fillMaxHeight()) {
        val width = size.width - 100f;
        val height = size.height;
        val waterWavesYPosition = (1 - waterPercentageFilled.value) * width
        var waterPath = Path().apply {
            moveTo(
                x = width / 6,
                y = 0f
            )
            lineTo(
                x = width*1f,
                y =  0f
            )
            cubicTo(x1 =width*1f, y1 = 0f, x2 = width*1f, y2 = height/2, x3 = width*0.88f, y3 = height)
            lineTo(
                x =width*0.80f,
                y = height
            )

            lineTo(
                x = width / 4,
                y =height
            )
            cubicTo(x1 =width / 4, y1 = height, x2 = width/7, y2 = height/2, x3 = width / 6, y3 = 0f)


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

@Composable
fun Glass(
    screenWidth: Dp, screenHeight: Dp, waterPercentageFilled: State<Float>
) {
    Canvas(modifier = Modifier
        .width(
            screenWidth.value.dp
        )
        .fillMaxHeight()) {
        val width = size.width - 100f;
        val height = size.height;
        val waterWavesYPosition = (1 - waterPercentageFilled.value) * width
        var waterPath = Path().apply {
            moveTo(
                x = width / 6,
                y = 0f
            )
            lineTo(
                x = width*1f,
                y =  0f
            )
            cubicTo(x1 =width*1f, y1 = 0f, x2 = width*1f, y2 = height/2, x3 = width*0.88f, y3 = height)
            lineTo(
                x =width*0.80f,
                y = height
            )

            lineTo(
                x = width / 4,
                y =height
            )
            cubicTo(x1 =width / 4, y1 = height, x2 = width/7, y2 = height/2, x3 = width / 6, y3 = 0f)


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


@Composable
fun Glacier(
    screenWidth: Dp, screenHeight: Dp, waterPercentageFilled: State<Float>,modifier:Modifier=Modifier
) {

    Canvas(modifier =modifier.offset(x=30.dp)) {
        val width = size.width-150f;
        val height = size.height;
        val waterWavesYPosition = (1 - waterPercentageFilled.value) * width
        var waterPath = Path().apply {
            moveTo(
                x = width / 2,
                y = 0f
            )
            lineTo(
                x = width*0.56f,
                y = height*0.08f
            )
            lineTo(
                x = width*0.69f,
                y =height*0.13f
            )
            lineTo(
                x = width*0.77f,
                y = height*0.21f
            )
            lineTo(
                x = width*0.84f,
                y = height*0.20f
            )

            lineTo(
                x = width*0.90f,
                y = height*0.24f
            )
            lineTo(
                x = width*0.93f,
                y =  height*0.34f
            )
            lineTo(
                x =  width*0.82f,
                y = height*0.64f
            )
            lineTo(
                x = width*0.76f,
                y = height*0.69f
            )
            lineTo(
                x =  width*0.74f,
                y =  height*0.85f
            )
            lineTo(
                x = width*0.55f,
                y =height
            )
            lineTo(
                x = width*0.20f,
                y = height*0.70f
            )
            lineTo(
                x = width*0.08f,
                y =height*0.30f
            )
            lineTo(
                x = width*0.21f,
                y = height*0.17f
            )
            lineTo(
                x = width*0.32f,
                y =height*0.12f
            )
            lineTo(
                x =width*0.33f,
                y = height*0.08f
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
        onWaterTrackingResourceAmount = 0,

        onTotalWaterTrackingResourceAmount = 1200,
       modifier = Modifier, screenWidth = screenWidth.value.dp, screenHeight = 600.dp
    )
}