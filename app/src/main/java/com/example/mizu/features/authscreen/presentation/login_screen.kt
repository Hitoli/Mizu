package com.example.mizu.features.authscreen.presentation

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.waterColor

@Composable
fun LoginScreen(modifier: Modifier) {

    Box(modifier = modifier) {

        Column(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .background(
                        backgroundColor1.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .fillMaxWidth(0.9f)
                    .height(650.dp)
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
                        imageVector = ImageVector.vectorResource(R.drawable.mizulogo),
                        contentDescription = "MizuLogo",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(300.dp),
                        alpha = 0.5f,
                    )
                    Text(
                        text = "Welcome, to Mizu",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = minorColor,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier.fillMaxWidth()
                    )

                    Box( modifier = Modifier
                        .width(280.dp)
                        .height(50.dp).background(minorColor.copy(alpha = 0.4f), shape = RoundedCornerShape(20.dp))){
                        Text(
                            text = "Google Sign In",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = backgroundColor1,
                                textAlign = TextAlign.Center,
                            ), modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Box( modifier = Modifier
                        .width(280.dp)
                        .height(50.dp).background(minorColor.copy(alpha = 0.4f), shape = RoundedCornerShape(20.dp))){
                        Text(
                            text = "Email Sign In",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = backgroundColor1,
                                textAlign = TextAlign.Center,
                            ), modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Box( modifier = Modifier
                        .width(280.dp)
                        .height(50.dp).background(minorColor.copy(alpha = 0.4f), shape = RoundedCornerShape(20.dp))){
                        Text(
                            text = "Phone Sign In",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = fontFamilyLight,
                                fontWeight = FontWeight(400),
                                color = backgroundColor1,
                                textAlign = TextAlign.Center,
                            ), modifier = Modifier.align(Alignment.Center)
                        )
                    }

//                    TextField(value = "", onValueChange = {
////                        if(it.length<4){
////
////                        }
//                    }, modifier = Modifier
//                        .fillMaxWidth()
//                        .height(60.dp), colors = TextFieldDefaults.colors(
//                        focusedTextColor = minorColor,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent,
//                        focusedContainerColor = backgroundColor1,
//                        unfocusedContainerColor = backgroundColor1.copy(alpha = 0.8f),
//                        disabledContainerColor = waterColor,
//                        focusedIndicatorColor = Color.Transparent,
//                        focusedLabelColor = waterColor,
//                    ), isError = "", maxLines = 1,
//                        shape = RoundedCornerShape(16.dp), placeholder = {
////                            Text(
////                                text = "Height... Enter in Cm",
////                                style = TextStyle(
////                                    fontSize = 15.sp,
////                                    fontFamily = fontFamilyLight,
////                                    fontWeight = FontWeight(400),
////                                    color = minorColor,
////
////                                    textAlign = TextAlign.Center,
////                                )
////                            )
//                        }, textStyle = TextStyle(
//                            fontSize = 20.sp,
//                            fontFamily = fontFamilyLight,
//                            fontWeight = FontWeight(200),
//                            color = minorColor,
//                            textAlign = TextAlign.Start,
//                        ), keyboardActions = KeyboardActions(
//                            onDone = {
////                                if(!check){
////                                    getNavigate()
////                                }
//
//                                focusManager.clearFocus()
//                            }
//                        ), keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.Number,
//                            imeAction = ImeAction.Done
//                        )
//
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//                    if(check){
//                        Text(
//                            text =checkText,
//                            style = TextStyle(
//                                fontSize = 10.sp,
//                                fontFamily = fontFamilyLight,
//                                fontWeight = FontWeight(400),
//                                color = Color.Red.copy(alpha = 0.8f),
//                                textAlign = TextAlign.Center,
//                            )
//                        )
//                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }

            }

            Spacer(modifier = Modifier.height(90.dp))

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCompScreen() {
    LoginScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(waterColor.copy(alpha = 0.6f), minorColor.copy(alpha = 0.3f))
                ),
            )
    )
}