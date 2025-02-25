package com.example.mizu.features.onboarding.presentation.activityMeasurement

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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.features.onboarding.utils.ActivityMeasurementData
import com.example.mizu.features.onboarding.utils.OnBoardingButtons
import com.example.mizu.features.onboarding.utils.OnboardingIndicator
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.mizuBlackLight
import com.example.mizu.ui.theme.onboardingBoxColor
import com.example.mizu.ui.theme.textFieldErrorColor
import com.example.mizu.ui.theme.waterColor

@Composable
fun OnBoardingActiveScreen(
    modifier: Modifier = Modifier,
    activityMeasurementData: ActivityMeasurementData,
    getActiveOutcome: (Int) -> Unit,
    getNavigate: () -> Unit,
    getBacK: () -> Unit,
)
{

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
                imageVector = ImageVector.vectorResource(R.drawable.acitivitymeasurement),
                contentDescription = "onBoarding Getting Weight and Height"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Calculate Amount",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(600),
                    color = mizuBlackLight,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Please Select your activity level. This helps us calculate the right amount of water",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(200),
                    color = mizuBlackLight,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
        OnboardingIndicator(onboardingNav = 1)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    onboardingBoxColor,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "How Active are you?",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .border(
                        width = 0.2.dp,
                        color = mizuBlack.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(
                        if (activityMeasurementData.onActivityOutcome == 0) mizuBlack else onboardingBoxColor,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        getActiveOutcome(0)
                    },
            ) {
                Text(
                    text = "5-6 Days of workout",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = if (activityMeasurementData.onActivityOutcome == 0) onboardingBoxColor else mizuBlack,
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .border(
                        width = 0.2.dp,
                        color = mizuBlack.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(
                        if (activityMeasurementData.onActivityOutcome == 1) mizuBlack else onboardingBoxColor,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        getActiveOutcome(1)
                    },
            ) {
                Text(
                    text = "2-3 Days of workout",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = if (activityMeasurementData.onActivityOutcome == 1) onboardingBoxColor else mizuBlack,
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .border(
                        width = 0.2.dp,
                        color = mizuBlack.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(
                        if (activityMeasurementData.onActivityOutcome == 2) mizuBlack else onboardingBoxColor,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        getActiveOutcome(2)
                    },
            ) {
                Text(
                    text = "Minimal Activity",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = if (activityMeasurementData.onActivityOutcome == 2) onboardingBoxColor else mizuBlack,
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }

            if (activityMeasurementData.checkError) {

                Text(
                    text = activityMeasurementData.onErrorText,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(200),
                        color = textFieldErrorColor,
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier.fillMaxWidth()
                )
            }

            OnBoardingButtons(getNavigate, getBacK)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnBoardingActiveScreen() {
    var ActiveOutcome by remember {
        mutableIntStateOf(2)
    }
    OnBoardingActiveScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(backgroundColor1, backgroundColor2)
                )
            ),
        getActiveOutcome = {
            ActiveOutcome = it
        },
        activityMeasurementData = ActivityMeasurementData(
            onActivityOutcome = 0,
            onErrorText = "",
            checkError = false
        ), getNavigate = {}, getBacK = {}
    )
}
