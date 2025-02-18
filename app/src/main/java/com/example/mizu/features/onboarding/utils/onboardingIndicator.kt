package com.example.mizu.features.onboarding.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.waterColor

@Composable
fun OnboardingIndicator(onboardingNav: Int) {
    LazyRow(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(3) {
            Indicator(
                width = if (onboardingNav == it) 40.dp else 20.dp,
                color = if (onboardingNav == it) waterColor else waterColor.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.width(3.dp))
        }
    }
}

@Composable
fun Indicator(width: Dp, color: Color) {
    Box(
        modifier = Modifier
            .background(color = color, shape = RoundedCornerShape(10.dp))
            .height(6.dp)
            .width(width)
            .border(0.2.dp, color = mizuBlack.copy(alpha = 0.5f), shape = RoundedCornerShape(10.dp))
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewOnboardingIndicator() {
    OnboardingIndicator(onboardingNav = 0)
}