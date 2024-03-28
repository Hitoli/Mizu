package com.example.mizu.features.onboarding.presentation

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.mizu.ui.theme.blackShadeColor
import com.example.mizu.ui.theme.buttonTextColor
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.textFieldColor

@Composable
fun OnBoardingActiveScreen(
    modifier: Modifier = Modifier, getActiveOutcome: (Int?) -> Unit,
    onActiveOutcome: Int?,
    onUserName: String,
    getNavigate: () -> Unit
) {

    Box(modifier = modifier) {
        Text(
            text = "Hi, ${onUserName} \uD83D\uDC4B",
            modifier=Modifier.padding(top = 50.dp, start = 24.dp),
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
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .background(
                        minorColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(0.9f)
                    .height(540.dp)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.activity_logo),
                        contentDescription = "NameLogo"
                    )
                    Text(
                        text = "How active are you?",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Column( modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = {
                                getActiveOutcome(0)
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(60.dp),
                            colors = ButtonDefaults.buttonColors(
                                if(onActiveOutcome==0){
                                 minorColor
                                }else{
                                    backgroundColor1
                                }),
                            shape = RoundedCornerShape(10.dp),border = if (onActiveOutcome==0)BorderStroke(1.dp,
                                backgroundColor1)else BorderStroke(0.dp, minorColor)
                        ) {
                            Text(
                                text = "5-6 days workout",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = fontFamilyLight,
                                    fontWeight = FontWeight(400),
                                    color =  if(onActiveOutcome==0){
                                       backgroundColor1
                                    }else{
                                        minorColor
                                    },

                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {
                                getActiveOutcome(1)
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(60.dp),
                            colors =  ButtonDefaults.buttonColors(
                                if(onActiveOutcome==1){
                                    minorColor
                                }else{
                                    backgroundColor1
                                }),
                            shape = RoundedCornerShape(10.dp),border = if (onActiveOutcome==1)BorderStroke(1.dp,
                                backgroundColor1)else BorderStroke(0.dp, minorColor)
                        ) {
                            Text(
                                text = "2-3 days workout",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = fontFamilyLight,
                                    fontWeight = FontWeight(400),
                                    color = if(onActiveOutcome==1){
                                        backgroundColor1
                                    }else{
                                        minorColor
                                    },

                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                                getActiveOutcome(2)
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(60.dp),
                            colors =  ButtonDefaults.buttonColors(
                                if(onActiveOutcome==2){
                                    minorColor
                                }else{
                                    backgroundColor1
                                }),
                            shape = RoundedCornerShape(10.dp), border = if (onActiveOutcome==2)BorderStroke(1.dp,
                                backgroundColor1)else BorderStroke(0.dp, minorColor)
                        ) {
                            Text(
                                text = "Not much Active",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = fontFamilyLight,
                                    fontWeight = FontWeight(400),
                                    color = if(onActiveOutcome==2){
                                        backgroundColor1
                                    }else{
                                        minorColor
                                    },

                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }



                    Button(
                        onClick = {
                            getNavigate()
                        },
                        modifier = Modifier
                            .width(212.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor1),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Done",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = minorColor,

                                textAlign = TextAlign.Center,
                            )
                        )
                    }

                }

            }

            Spacer(modifier = Modifier.height(90.dp))

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnBoardingActiveScreen() {
    var ActiveOutcome by remember {
        mutableIntStateOf(2)
    }
    OnBoardingActiveScreen(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
                colors = listOf(backgroundColor1, backgroundColor2)
            )
        ), getActiveOutcome = {
        ActiveOutcome = it!!
    },onActiveOutcome = ActiveOutcome, onUserName = "Hitesh", getNavigate = {

    })
}
