package com.example.mizu.features.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mizu.R
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.waterColorBackground

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .align(
                    Alignment.Center
                )
                .padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                imageVector = ImageVector.vectorResource(R.drawable.mizulogo),
                contentDescription = "MizuLogo",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY*0.4f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = mutableStateListOf(waterColorBackground, backgroundColor2)
                )
            )
    )
}