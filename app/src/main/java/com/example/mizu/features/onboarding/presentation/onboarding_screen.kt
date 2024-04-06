package com.example.mizu.features.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.runtime.mutableStateOf
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
import androidx.core.text.isDigitsOnly
import com.example.mizu.R
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.blackShadeColor
import com.example.mizu.ui.theme.buttonTextColor
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.fontName
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.textFieldColor
import com.example.mizu.ui.theme.waterColor

@Composable
fun OnBoardingScreen(getValue:(String?)->Unit, onValue:String?,onQuestionValue:String,getNavigate:()->Unit,modifier:Modifier=Modifier, check:Boolean, checkText:String) {

    Box(modifier) {

            val focusManager = LocalFocusManager.current
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier
                    .background(
                        minorColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(0.9f)
                    .height(290.dp)
                    .padding(16.dp) ){
                    Column(modifier= Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.mizunamelogo),
                            contentDescription = "NameLogo"
                        )
                        Text(
                            text = onQuestionValue,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = backgroundColor1,
                                textAlign = TextAlign.Center,
                            ),modifier=Modifier.fillMaxWidth()
                        )
                        TextField(value = onValue.toString(), onValueChange = {
                                getValue(it)
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp), colors = TextFieldDefaults.colors(
                            focusedTextColor = minorColor,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = backgroundColor1,
                            unfocusedContainerColor = backgroundColor1.copy(alpha = 0.8f),
                            disabledContainerColor = waterColor,
                                focusedIndicatorColor = Color.Transparent,
                                focusedLabelColor = waterColor,
                        ),maxLines = 1, shape = RoundedCornerShape(8.dp), placeholder = {
                            Text(
                                text = "Name...",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = fontFamilyLight,
                                    fontWeight = FontWeight(400),
                                    color = minorColor,

                                    textAlign = TextAlign.Center,
                                )
                            )
                        }, textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(200),
                            color = minorColor,
                            textAlign = TextAlign.Start,
                        ), isError = check, keyboardActions = KeyboardActions(
                            onDone = {
                               if(!check){
                                   getNavigate()
                               }
                                focusManager.clearFocus()
                            }
                        ), keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        if(check){
                            Text(
                                text =checkText,
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = fontFamilyLight,
                                    fontWeight = FontWeight(400),
                                    color = Color.Red.copy(alpha = 0.8f),
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))


                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = {
                        if(!check){
                            getNavigate()
                        }
                        focusManager.clearFocus()

                    },
                    modifier = Modifier
                        .width(180.dp)
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
    } , onValue =name , onQuestionValue ="What is your name?", getNavigate = {}, modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
                colors = listOf(backgroundColor1, backgroundColor2)
            )
        ),check = true, checkText = "Name field can not be empty or contain number / Special characters")
}