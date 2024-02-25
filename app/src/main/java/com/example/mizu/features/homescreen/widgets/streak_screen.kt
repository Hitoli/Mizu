package com.example.mizu.features.homescreen.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.waterColor

@Composable
fun StreakScreen(modifier: Modifier=Modifier,Streak:String,username:String,getStreak:()->Unit) {
    val LocalConfig = LocalConfiguration.current
    val screenWidth = LocalConfig.screenWidthDp.dp
    val screenHeight = LocalConfig.screenHeightDp.dp
    Box(modifier = Modifier){

        Canvas(
            modifier = Modifier
                .width(screenWidth.value * 0.3.dp)
                .height(screenHeight.value * 0.05.dp)
                .clip(RoundedCornerShape(40.dp))
                .border(width = 0.5.dp, color = minorColor, shape = RoundedCornerShape(40.dp))
                .align(
                    Alignment.TopStart
                )
        ) {
            val width = size.width
            val height = size.height
            val waterWavesYPosition = width
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
            text = username,
            modifier = Modifier
                .width(screenWidth.value*0.25.dp)
                .align(Alignment.Center),
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight(500),
                color = minorColor,
                textAlign = TextAlign.Start,
            )
        )

        Box(modifier = Modifier.align(Alignment.CenterEnd)){
            Text(
                text = Streak,
                modifier = Modifier
                    .width(60.dp)
                    .align(Alignment.Center),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(500),
                    color = minorColor,
                    textAlign = TextAlign.Center,
                )
            )
            Canvas(
                modifier = Modifier.size(screenHeight.value * 0.05.dp).align(Alignment.Center),
                onDraw = {

                    val strokeWidth = .5f
                    drawCircle(
                        color = minorColor,
                        radius = screenHeight.value * 0.05f,
                        style = Stroke(
                            width = strokeWidth
                        )
                    )

                }
            )
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStreakScreen() {
    StreakScreen(Streak = "6", username = "Hitesh", getStreak = {

    })

}