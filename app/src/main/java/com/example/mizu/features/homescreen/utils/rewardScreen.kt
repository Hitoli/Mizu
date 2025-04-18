package com.example.mizu.features.homescreen.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mizu.R
import com.example.mizu.ui.theme.blackShadeColor
import com.example.mizu.ui.theme.buttonTextColor
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.textFieldColor

//import com.airbnb.lottie.compose.LottieAnimation
//import com.airbnb.lottie.compose.LottieCompositionSpec
//import com.airbnb.lottie.compose.LottieConstants
//import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun RewardScreen(getNavigate: () -> Unit, modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.waterfall))

    Box(modifier = modifier) {

        Column(
            modifier = Modifier.align(Alignment.Center), verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .width(220.dp)
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = "You have achieved today's target",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = buttonTextColor,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(10.dp))

            Button(
                onClick = {
                    getNavigate()
                },
                modifier = Modifier
                    .width(212.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(textFieldColor),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Okay",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        color = blackShadeColor,

                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRewardScreen() {
    RewardScreen(
        getNavigate = { }, modifier = Modifier
            .background(
                blackShadeColor.copy(alpha = 0.7f),
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth(0.9f)
            .height(400.dp)
            .padding(16.dp)
    )
}