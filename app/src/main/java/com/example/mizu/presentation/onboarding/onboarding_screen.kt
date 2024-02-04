package com.example.mizu.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.blackShadeColor
import com.example.mizu.ui.theme.buttonTextColor
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.textFieldColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
)
@Composable
fun OnBoardingScreen(getValue:(String?)->Unit, onValue:String?,onQuestionValue:String,getNavigate:()->Unit,modifier:Modifier=Modifier) {

    Box(modifier) {

            val focusManager = LocalFocusManager.current
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier
                    .background(
                        blackShadeColor.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(0.9f)
                    .height(200.dp)
                    .padding(16.dp) ){
                    Column(modifier= Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "What is your name?",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(400),
                                color = buttonTextColor,
                                textAlign = TextAlign.Center,
                            ),modifier=Modifier.fillMaxWidth()
                        )
                        TextField(value = onValue.toString(), onValueChange ={
                            getValue(it)
                        },modifier= Modifier
                            .fillMaxWidth()
                            .height(60.dp), colors = TextFieldDefaults.textFieldColors(textFieldColor), shape = RoundedCornerShape(16.dp), placeholder = {
                            Text(
                                text = "Name...",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight(400),
                                    color = blackShadeColor,

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
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = { getNavigate()
                        focusManager.clearFocus()
                    },
                    modifier = Modifier
                        .width(212.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(blackShadeColor),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Done",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(400),
                            color = buttonTextColor,

                            textAlign = TextAlign.Center,
                        )
                    )
                }
                Spacer(modifier = Modifier.height(90.dp))

            }





    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnBoardingScreen() {
    var name by remember{
        mutableStateOf("")
    }
    OnBoardingScreen(getValue ={
                               name = it!!
    } , onValue =name , onQuestionValue ="", getNavigate = {}, modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
                colors = listOf(backgroundColor1, backgroundColor2)
            )
        ))
}