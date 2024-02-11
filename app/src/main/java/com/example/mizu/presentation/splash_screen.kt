package com.example.mizu.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily

@Composable
fun SplashScreen(modifier:Modifier=Modifier) {

    Box(modifier = modifier){

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .align(
                Alignment.Center
            )
            .padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id = R.drawable.mizusplash), contentDescription = "Mizu Logo on Splash",
                modifier = Modifier

                    .size(300.dp),
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Your Personal Water Coach",
                modifier= Modifier,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(300),
                    color = Color(0xFF29302C),
                    textAlign = TextAlign.Center,
                )
            )
        }



    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
                colors = listOf(backgroundColor1, backgroundColor2)
            )
        ))
}