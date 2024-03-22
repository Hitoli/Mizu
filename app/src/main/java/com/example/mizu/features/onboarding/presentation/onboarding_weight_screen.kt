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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalFocusManager
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
import com.example.mizu.ui.theme.blackShadeColor
import com.example.mizu.ui.theme.buttonTextColor
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.textFieldColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingWeightScreen(
    modifier: Modifier = Modifier,
    getWeightChange: (Int?) -> Unit,
    onWeightChange: Int?,
    onUserName: String,
    getNavigate: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(modifier = modifier) {
        Text(
            text = "Hi, Hitesh \uD83D\uDC4B",
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .background(
                        minorColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(0.9f)
                    .height(350.dp)
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
                        imageVector = ImageVector.vectorResource(R.drawable.mizunamelogo),
                        contentDescription = "NameLogo"
                    )
                    Text(
                        text = "What is your Weight?",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier.fillMaxWidth()
                    )

                    TextField(value = onWeightChange.toString(), onValueChange = {
                        getWeightChange(it.toInt())
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp), colors = TextFieldDefaults.colors(
                        focusedTextColor = backgroundColor1,
                        focusedContainerColor = backgroundColor1,
                        unfocusedContainerColor = backgroundColor1,
                        disabledContainerColor = backgroundColor1,
                    ), shape = RoundedCornerShape(16.dp), placeholder = {
                        Text(
                            text = "Weight...",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = minorColor,

                                textAlign = TextAlign.Center,
                            )
                        )
                    }, textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(200),
                        color = blackShadeColor,
                        textAlign = TextAlign.Start,
                    ), keyboardActions = KeyboardActions(
                        onDone = {
                            getNavigate()
                            focusManager.clearFocus()
                        }
                    ), keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    )
                    )
                    Button(
                        onClick = {
                            getNavigate()

                            focusManager.clearFocus()
                        },
                        modifier = Modifier
                            .width(212.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(textFieldColor),
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
fun PreviewOnBoardingWeightScreen() {
    var weightValue by remember {
        mutableIntStateOf(0)
    }
    OnBoardingWeightScreen(getWeightChange = {
        if (it != null) {
            weightValue = it
        }
    }, onWeightChange = weightValue, onUserName = "Hitesh", getNavigate = {
                                                                          println("Naavigationg")
    }, modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
                colors = listOf(backgroundColor1, backgroundColor2)
            )
        )
    )
}
