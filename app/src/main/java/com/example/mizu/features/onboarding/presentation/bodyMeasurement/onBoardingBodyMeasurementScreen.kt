package com.example.mizu.features.onboarding.presentation.bodyMeasurement

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.mizu.features.onboarding.utils.BodyMeasurementData
import com.example.mizu.features.onboarding.utils.OnboardingIndicator
import com.example.mizu.features.onboarding.utils.TextFieldCustom
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.mizuBlackLight
import com.example.mizu.ui.theme.onboardingBoxColor
import com.example.mizu.ui.theme.waterColor
import com.example.mizu.ui.theme.waterColorBackground
import com.example.mizu.ui.theme.waterColorMeter

@Composable
fun OnBoardingBodyMeasurementsScreen(
    modifier: Modifier = Modifier,
    bodyMeasurementData: BodyMeasurementData,
    getWeightChange: (String) -> Unit,
    getHeightChange: (String) -> Unit,
    getNavigate: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .wrapContentSize()
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.drinkwater),
                contentDescription = "onBoarding Getting Weight and Height"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Right Hydration",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(600),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = "Please enter your weight and height. This helps us recommend the right hydration plan for you!",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(200),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
        }
        OnboardingIndicator(onboardingNav = 0)
        Column(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxWidth()
                .background(
                    onboardingBoxColor,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                ).border(width = 0.1.dp, color = mizuBlackLight, shape =RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp) )
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "What is your height and weight?",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            TextFieldCustom(
                onTextChange = bodyMeasurementData.onWeightChange,
                checkError = bodyMeasurementData.onWeightCheck,
                onErrorText = bodyMeasurementData.onWeightError,
                onPlaceHolderText = "Enter Weight in Kgs",
                getTextChange = getWeightChange,
                onLabelText = "Weight",
                onImeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(6.dp))

            TextFieldCustom(
                onTextChange = bodyMeasurementData.onHeightChange,
                checkError = bodyMeasurementData.onHeightCheck,
                onErrorText = bodyMeasurementData.onHeightError,
                onPlaceHolderText = "Enter Height in Cms",
                getTextChange = getHeightChange,
                onLabelText = "Height",
                onImeAction = ImeAction.Done
            )

            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier
                    .clickable {
                        getNavigate()
                    }
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
                    .background(waterColor, shape = RoundedCornerShape(6.dp)),
            ) {
                Text(
                    text = "Next",
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


}


@Preview(showBackground = true)
@Composable
fun PreviewOnBoardingWeightScreen() {
    var weightValue by remember {
        mutableStateOf("")
    }
    OnBoardingBodyMeasurementsScreen(
        getWeightChange = {
                weightValue = it
        },
        getNavigate = {
        },
        getHeightChange = {

        },
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(waterColorBackground, backgroundColor2)
                )
            ),
        bodyMeasurementData = BodyMeasurementData(
            onWeightChange = "",
            onHeightChange = "",
            onWeightCheck = false,
            onHeightCheck = false,
            onHeightError = "",
            onWeightError = ""
        )
    )
}
