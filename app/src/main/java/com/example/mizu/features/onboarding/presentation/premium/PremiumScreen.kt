package com.example.mizu.features.onboarding.presentation.premium

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.onboardingBoxColor
import com.example.mizu.ui.theme.waterColor
import com.example.mizu.ui.theme.waterColorMeter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumScreen(modifier: Modifier = Modifier) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Subscribe to premium",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier.fillMaxWidth()
                )
            }, navigationIcon = {
                Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Arrow")
            }, colors = TopAppBarDefaults.topAppBarColors(waterColorMeter.copy(alpha = 0.1f)))
        }
    ) {
        val pad = it
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = pad.calculateTopPadding(),
                    bottom = pad.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(modifier = Modifier
                .wrapContentSize()
                ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.White, shape = RoundedCornerShape(12.dp))
                            .wrapContentSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Monthly",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(400),
                                color = mizuBlack,
                            ), modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "$9.99",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(600),
                                color = mizuBlack,
                            ), modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = "Billed Monthly",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(400),
                                color = mizuBlack,
                            ), modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.width(25.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.White, shape = RoundedCornerShape(12.dp))
                            .wrapContentSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Lifetime",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(400),
                                color = mizuBlack,
                            ), modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "$12.00",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(600),
                                color = mizuBlack,
                            ), modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = "Billed One Time",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(400),
                                color = mizuBlack,
                            ), modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(80.dp))

                Column(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "• Get Access to Home Screen Widget",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(600),
                            color = mizuBlack,
                        ), modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "• Set Custom Daily Reminders",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(600),
                            color = mizuBlack,
                        ), modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "• Advanced Tracking and Analytics",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(600),
                            color = mizuBlack,
                        ), modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "• Ad-Free Experience",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(600),
                            color = mizuBlack,
                        ), modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                }
            }



            Column(modifier= Modifier
                .wrapContentSize()
                ) {
                Box(
                    modifier = Modifier
                        .clickable {
//                        getNavigate()
                        }
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .background(waterColor, shape = RoundedCornerShape(6.dp)),
                ) {
                    Text(
                        text = "Subscribe",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = mizuBlack,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    )


                }

                Text(
                    text = "Skip",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier
                        .fillMaxWidth()
                )
            }


        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewPremiumScreen() {
    PremiumScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(waterColorMeter.copy(alpha = 0.2f), backgroundColor2)
                )
            )
    )
}