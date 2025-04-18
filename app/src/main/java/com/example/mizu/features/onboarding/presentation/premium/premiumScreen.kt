package com.example.mizu.features.onboarding.presentation.premium

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.mizu.features.onboarding.utils.premiumData
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.waterColor
import com.example.mizu.ui.theme.waterColorMeter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumScreen(
    modifier: Modifier = Modifier,
    getBack: () -> Unit,
    getSubscribe: () -> Unit,
    getSkip: () -> Unit,
    onPremiumData: premiumData
) {

    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = Color.Transparent,
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
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = "Arrow",
                    modifier = Modifier.clickable {
                        getBack()
                    })
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
                    bottom = pad.calculateBottomPadding() + 60.dp
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
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
                            text = "₹ ${onPremiumData.onMonthlyPrice}",
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
                            text = "One Time",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(400),
                                color = mizuBlack,
                            ), modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "₹ ${onPremiumData.onLifeTimePrice}",
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

                LazyColumn(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(onPremiumData.onListOfPremiumBenefits.size) {
                        Text(
                            text = onPremiumData.onListOfPremiumBenefits[it],
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
            }



            Column(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Box(
                    modifier = Modifier
                        .clickable(interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null, onClick = {
                            getSubscribe()
                        })
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
                        fontSize = 14.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .clickable(interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null, onClick = {
                            getSkip()
                        })
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
            ), getBack = {}, getSubscribe = {}, getSkip = {}, onPremiumData = premiumData(
            onMonthlyPrice = "", onLifeTimePrice = "", onListOfPremiumBenefits =
            listOf(
                "Get Access to Home Screen Widget",
                "Set Custom Daily Reminders",
                "Advanced Tracking and Analytics",
                "Ad-Free Experience"
            )
        )
    )
}