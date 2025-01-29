package com.example.mizu.features.onboarding.presentation.premium

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.mizu.ui.theme.mizuBlack
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
            })
        }
    ) {
        val pad = it
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Monthly",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(400),
                            color = mizuBlack,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.width(25.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(400),
                            color = mizuBlack,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier.fillMaxWidth()
                    )
                }
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