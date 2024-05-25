package com.example.mizu.features.profilescreen.presentation


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.waterColor


@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier, getBack: () -> Unit, imgModifier: Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(backgroundColor1),
                title = {},
                navigationIcon = {
                    IconButton(onClick = { getBack() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.back_arrow),
                            contentDescription = "Arrow Back",
                            tint = minorColor
                        )
                    }
                },
            )
        },
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .heightIn(40.dp)
                        .widthIn(300.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(1.dp, minorColor)
                ) {
                    Text(
                        text = "Save",
                        fontSize = 14.sp,
                        color = minorColor,
                        fontFamily = fontFamilyLight
                    )
                }
            }

        }
    ) {
        val pad = it
        Column(
            modifier = modifier
                .padding(top = pad.calculateTopPadding(), bottom = pad.calculateBottomPadding())
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .background(color = minorColor, shape = RoundedCornerShape(16.dp))
                    .fillMaxWidth(0.9f)
                    .heightIn(110.dp)
            ) {
                Text(
                    text = "Account",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(20.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = backgroundColor1,
                        textAlign = TextAlign.Center,
                    )
                )


                Image(
                    painter = painterResource(id = R.drawable.mizunamelogo),
                    contentDescription = "UserIcon",
                    modifier = imgModifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 30.dp)
                )


            }
//            Spacer(modifier = Modifier.height(50.dp))
//
//            Column(
//                modifier = Modifier
//                    .background(color = minorColor, shape = RoundedCornerShape(16.dp))
//                    .fillMaxWidth(0.9f)
//                    .fillMaxHeight(0.2f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
//                Text(
//                    text = "Stats",
//                    modifier=Modifier.padding(top = 4.dp),
//                    style = TextStyle(
//                        fontSize = 20.sp,
//                        fontFamily = fontFamilyLight,
//                        fontWeight = FontWeight(400),
//                        color = backgroundColor1,
//                        textAlign = TextAlign.Center,
//                    )
//                )
//                Spacer(modifier = Modifier.height(20.dp))
//                Row(modifier= Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 4.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
//                    Column(modifier=Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
//                        Text(
//                            text = "6",
//                            modifier=Modifier,
//                            style = TextStyle(
//                                fontSize = 16.sp,
//                                fontFamily = fontFamilyLight,
//                                fontWeight = FontWeight(400),
//                                color = backgroundColor1,
//                                textAlign = TextAlign.Center,
//                            )
//                        )
//                        Text(
//                            text = "Streak",
//                            modifier=Modifier,
//                            style = TextStyle(
//                                fontSize = 16.sp,
//                                fontFamily = fontFamilyLight,
//                                fontWeight = FontWeight(400),
//                                color = backgroundColor1,
//                                textAlign = TextAlign.Center,
//                            )
//                        )
//                    }
//                    Column(modifier=Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
//                        Text(
//                            text = "6",
//                            modifier=Modifier,
//                            style = TextStyle(
//                                fontSize = 16.sp,
//                                fontFamily = fontFamilyLight,
//                                fontWeight = FontWeight(400),
//                                color = backgroundColor1,
//                                textAlign = TextAlign.Center,
//                            )
//                        )
//                        Text(
//                            text = "Shares",
//                            modifier=Modifier,
//                            style = TextStyle(
//                                fontSize = 16.sp,
//                                fontFamily = fontFamilyLight,
//                                fontWeight = FontWeight(400),
//                                color = backgroundColor1,
//                                textAlign = TextAlign.Center,
//                            )
//                        )
//                    }
//                    Column(modifier=Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
//                        Text(
//                            text = "6",
//                            modifier=Modifier,
//                            style = TextStyle(
//                                fontSize = 16.sp,
//                                fontFamily = fontFamilyLight,
//                                fontWeight = FontWeight(400),
//                                color = backgroundColor1,
//                                textAlign = TextAlign.Center,
//                            )
//                        )
//                        Text(
//                            text = "Goals",
//                            modifier=Modifier,
//                            style = TextStyle(
//                                fontSize = 16.sp,
//                                fontFamily = fontFamilyLight,
//                                fontWeight = FontWeight(400),
//                                color = backgroundColor1,
//                                textAlign = TextAlign.Center,
//                            )
//                        )
//                    }
//                }
//
//
//
//            }
            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = "Name",
                    modifier = Modifier.padding(top = 4.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = minorColor,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 10.dp,
                            ambientColor = minorColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(backgroundColor2, shape = RoundedCornerShape(16.dp))
                        .border(
                            border = BorderStroke(
                                1.dp,
                                minorColor
                            ), shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    TextField(
                        value = "Hitesh",
                        onValueChange = {

                        },
                        colors = TextFieldDefaults.colors(
                            errorContainerColor = Color.Red.copy(alpha = 0.4f),
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red.copy(alpha = 0.4f),
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = minorColor,
                            unfocusedTextColor = minorColor
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Weight",
                    modifier = Modifier.padding(top = 4.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = minorColor,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 10.dp,
                            ambientColor = minorColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(backgroundColor2, shape = RoundedCornerShape(16.dp))
                        .border(
                            border = BorderStroke(
                                1.dp,
                                minorColor
                            ), shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    TextField(
                        value = "72",
                        onValueChange = {

                        },
                        colors = TextFieldDefaults.colors(
                            errorContainerColor = Color.Red.copy(alpha = 0.4f),
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red.copy(alpha = 0.4f),
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = minorColor,
                            unfocusedTextColor = minorColor
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Height",
                    modifier = Modifier.padding(top = 4.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = minorColor,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 10.dp,
                            ambientColor = minorColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(backgroundColor2, shape = RoundedCornerShape(16.dp))
                        .border(
                            border = BorderStroke(
                                1.dp,
                                minorColor
                            ), shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    TextField(
                        value = "172",
                        onValueChange = {

                        },
                        colors = TextFieldDefaults.colors(
                            errorContainerColor = Color.Red.copy(alpha = 0.4f),
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red.copy(alpha = 0.4f),
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = minorColor, unfocusedTextColor = minorColor
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

            }

        }


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(backgroundColor1, backgroundColor2)
                )
            )
            .padding(top = 20.dp), getBack = {}, imgModifier = Modifier
    )
}