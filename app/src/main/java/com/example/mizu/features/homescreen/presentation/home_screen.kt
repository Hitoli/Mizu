package com.example.mizu.features.homescreen.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.window.Dialog
import com.example.mizu.features.homescreen.widgets.GlacierScreen
import com.example.mizu.features.homescreen.widgets.RewardScreen
import com.example.mizu.features.homescreen.widgets.StreakScreen
import com.example.mizu.features.homescreen.widgets.WaterProgressScreen
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.blackShadeColor
import com.example.mizu.ui.theme.buttonTextColor
import com.example.mizu.ui.theme.circleWaterIndicatorColor
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.waterColor

@Composable
fun HomeScreen(
    onPad: PaddingValues,
    onWaterTrackingResourceAmount: Int,
    getWaterTrackingResourceAmount: (Int) -> Unit,
    onTotalWaterTrackingResourceAmount: Int,
    getAddWater: () -> Unit,
    onUserName: String,
    modifier: Modifier = Modifier,
    getReward: (Boolean?) -> Unit,
    onReward: Boolean?
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

        if (onReward!!) {
            DialogRewardScreen(getReward = {
                getReward(it)
            })
        }

            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {

                        WaterProgressScreen(
                            onWaterTrackingResourceAmount = 1000,
                            getWaterTrackingResourceAmount = {

                            },
                            onTotalWaterTrackingResourceAmount = 2500,
                            getAddWater = {},
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "You are half way through keep it going.",
                            modifier = Modifier.width(220.dp),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = minorColor,
                                textAlign = TextAlign.Start,
                            )
                        )


                    }
                    StreakScreen(Streak = "6\uD83D\uDD25", username = onUserName, getStreak = {

                    })

                }
               GlacierScreen(
                   onWaterTrackingResourceAmount = onWaterTrackingResourceAmount,
                   getWaterTrackingResourceAmount = {},
                   onTotalWaterTrackingResourceAmount =onTotalWaterTrackingResourceAmount ,
                   getAddWater = { /*TODO*/ },
                   screenWidth = screenWidth.value.dp,
                   screenHeight =450.dp
               )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            getAddWater()
                            getReward(true)
                        },
                        modifier = Modifier
                            .width(screenWidth.value * 0.35.dp)
                            .height(screenHeight.value * 0.06.dp),
                        colors = ButtonDefaults.buttonColors(blackShadeColor),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Share",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = backgroundColor1,

                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            getAddWater()
                            getReward(true)
                        },
                        modifier = Modifier
                            .width(screenWidth.value * 0.35.dp)
                            .height(screenHeight.value * 0.06.dp),
                        colors = ButtonDefaults.buttonColors(blackShadeColor),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "250 ml",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = backgroundColor1,

                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }



            }


    }


}

@Composable
fun DialogRewardScreen(getReward: (Boolean) -> Unit) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        RewardScreen(
            getNavigate = {
                getReward(false)
            }, modifier = Modifier
                .background(
                    blackShadeColor.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth(0.9f)
                .height(400.dp)
                .padding(16.dp)
        )
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {

    var rewardScreenShow by remember {
        mutableStateOf(false)
    }
    HomeScreen(
        onPad = PaddingValues(20.dp),
        onWaterTrackingResourceAmount = 200,
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
            ), onReward = rewardScreenShow, getReward = {
            if (it != null) {
                rewardScreenShow = it
            }
        }
    )
}