package com.example.mizu.features.onboarding.presentation.permissionScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.mizu.features.onboarding.utils.OnBoardingButtons
import com.example.mizu.features.onboarding.utils.OnboardingIndicator
import com.example.mizu.features.onboarding.utils.SingleButton
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.mizuBlackLight
import com.example.mizu.ui.theme.onboardingBoxColor
import com.example.mizu.ui.theme.textFieldErrorColor
import com.example.mizu.ui.theme.waterColor
import com.example.mizu.ui.theme.waterColorBackground

@Composable
fun OnboardingReminder(modifier:Modifier = Modifier, getAllow:()->Unit, onPermissionDenied:Boolean) {

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
                imageVector = ImageVector.vectorResource(R.drawable.bg_sip_to_scroll),
                contentDescription = "Overlay permission background",
                modifier = Modifier.size(180.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Scroll to Sip, Sip to Scroll",
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
                text = "Every few swipes, we’ll remind you to drink water. Your feed unlocks as you stay hydrated",
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
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    onboardingBoxColor,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Reminders",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "To remind you to drink water while using social media, we need permission to overlay a small hydration reminder on your screen.",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(400),
                    color = mizuBlack,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.fillMaxWidth()
            )

//            if (activityMeasurementData.checkError) {
//
//                Text(
//                    text = activityMeasurementData.onErrorText,
//                    style = TextStyle(
//                        fontSize = 10.sp,
//                        fontFamily = fontFamilyLight,
//                        fontWeight = FontWeight(200),
//                        color = textFieldErrorColor,
//                        textAlign = TextAlign.Center,
//                    ), modifier = Modifier.fillMaxWidth()
//                )
//            }
            Spacer(modifier = Modifier.height(30.dp))
            SingleButton(getNavigate = {getAllow()}, buttonName ="Allow" )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewOnboardingReminderPermission() {
    OnboardingReminder(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                start = Offset(Float.POSITIVE_INFINITY * 0.4f, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
                colors = listOf(waterColorBackground, backgroundColor2)
            )
        ), getAllow = {},onPermissionDenied = false)
}