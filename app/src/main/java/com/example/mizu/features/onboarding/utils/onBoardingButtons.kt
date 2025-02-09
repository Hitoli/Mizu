package com.example.mizu.features.onboarding.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.onboardingBoxColor
import com.example.mizu.ui.theme.waterColor

@Composable
fun OnBoardingButtons(getNavigate:()->Unit ,getBack:()->Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(90.dp)
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .border(width = 0.2.dp, color = mizuBlack, shape = RoundedCornerShape(6.dp))
                .background(onboardingBoxColor, shape = RoundedCornerShape(6.dp)).clickable {
                                                                                            getBack()
                },
        ) {
            Text(
                text = "Back",
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
        Box(
            modifier = Modifier.clickable {
                getNavigate()
            }
                .weight(1f)
                .height(90.dp)
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .background(waterColor, shape = RoundedCornerShape(6.dp)),
        ) {
            Text(
                text = "Done",
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
    }
}