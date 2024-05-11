package com.example.mizu.features.profilescreen.presentation

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .background(color = minorColor, shape = RoundedCornerShape(16.dp))
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.3f)) {
            Text(
                text = "Account",
                modifier= Modifier
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
                    modifier= Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 30.dp)
                )



        }
        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier
                .background(color = minorColor, shape = RoundedCornerShape(16.dp))
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.2f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = "Stats",
                modifier=Modifier.padding(top = 4.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(400),
                    color = backgroundColor1,
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                Column(modifier=Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "6",
                        modifier=Modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        text = "Streak",
                        modifier=Modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
                Column(modifier=Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "6",
                        modifier=Modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        text = "Shares",
                        modifier=Modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
                Column(modifier=Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "6",
                        modifier=Modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        text = "Goals",
                        modifier=Modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }



        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier=Modifier,horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly) {

            Text(
                text = "Email",
                modifier=Modifier.padding(top = 4.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(400),
                    color = minorColor,
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box (modifier= Modifier.shadow(elevation = 10.dp, ambientColor = minorColor, shape = RoundedCornerShape(16.dp))
                .background(backgroundColor2, shape = RoundedCornerShape(16.dp))
                .border(
                    border = BorderStroke(
                        1.dp,
                        minorColor
                    ), shape = RoundedCornerShape(16.dp)
                )){
                TextField(
                    value = "email@gmail.com",
                    onValueChange = {

                    },
                    colors =TextFieldDefaults.colors(errorContainerColor = Color.Red.copy(alpha = 0.4f), unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent, disabledContainerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, errorIndicatorColor = Color.Red.copy(alpha = 0.4f), disabledIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Mobile",
                modifier=Modifier.padding(top = 4.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(400),
                    color = minorColor,
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box (modifier= Modifier.shadow(elevation = 10.dp, ambientColor = minorColor, shape = RoundedCornerShape(16.dp))
                .background(backgroundColor2, shape = RoundedCornerShape(16.dp))
                .border(
                    border = BorderStroke(
                        1.dp,
                        minorColor
                    ), shape = RoundedCornerShape(16.dp)
                )){
                TextField(
                    value = "773773778282",
                    onValueChange = {

                    },
                    colors =TextFieldDefaults.colors(errorContainerColor = Color.Red.copy(alpha = 0.4f), unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent, disabledContainerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, errorIndicatorColor = Color.Red.copy(alpha = 0.4f), disabledIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Hitesh Kohli",
                modifier=Modifier.padding(top = 4.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(400),
                    color = minorColor,
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box (modifier= Modifier.shadow(elevation = 10.dp, ambientColor = minorColor, shape = RoundedCornerShape(16.dp))
                .background(backgroundColor2, shape = RoundedCornerShape(16.dp))
                .border(
                    border = BorderStroke(
                        1.dp,
                        minorColor
                    ), shape = RoundedCornerShape(16.dp)
                )){
                TextField(
                    value = "email@gmail.com",
                    onValueChange = {

                    },
                    colors =TextFieldDefaults.colors(errorContainerColor = Color.Red.copy(alpha = 0.4f), unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent, disabledContainerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, errorIndicatorColor = Color.Red.copy(alpha = 0.4f), disabledIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent)
                )
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
            .padding(top = 20.dp)
    )
}