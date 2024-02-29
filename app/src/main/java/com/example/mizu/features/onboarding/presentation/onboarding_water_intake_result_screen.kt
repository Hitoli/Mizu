package com.example.mizu.features.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor

@Composable
fun OnBoardingWaterIntakeResultScreen(getNavigate:()->Unit,onWaterIntake:String,modifier:Modifier=Modifier) {
    Box(modifier = modifier) {
        Text(
            text = "Hi, Hitesh \uD83D\uDC4B",
            modifier= Modifier.padding(top = 50.dp, start = 24.dp),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = fontFamilyLight,
                fontWeight = FontWeight(400),
                color = minorColor,
                textAlign = TextAlign.Center,
            )
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .background(
                        minorColor.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(0.9f)
                    .height(300.dp)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.waterintakevaluelogo),
                        contentDescription = "NameLogo"
                    )
                    Text(
                        text = "Your Water Intake:",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = onWaterIntake,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier.fillMaxWidth()
                    )



                }

            }


            Spacer(modifier = Modifier.height(90.dp))
            Button(
                onClick = {
                    getNavigate()
                },
                modifier = Modifier
                    .width(212.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(minorColor),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Done",
                    style = TextStyle(
                        fontSize = 24.sp,
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreivewOnBoardingWaterIntakeResultScreen() {
    OnBoardingWaterIntakeResultScreen(getNavigate = {}, onWaterIntake = "2500", modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
                colors = listOf(backgroundColor1, backgroundColor2)
            )
        ))
}