package com.example.mizu.features.homescreen.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.R
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamily
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.waterColor

@Composable
fun IntakeProgressScreen(modifier:Modifier=Modifier, onAvgWaterPercent:String,onAvgWaterIntake:String){
        Row(modifier=modifier, horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = onAvgWaterIntake,
                    modifier = Modifier,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = Color.White,

                        textAlign = TextAlign.Start,
                    )
                )
                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = onAvgWaterPercent,
                    modifier = Modifier,
                    style = TextStyle(
                        fontSize = 42.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(700),
                        color = Color.White,

                        textAlign = TextAlign.Center,
                    )
                )
            }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewIntakeProgressScreen() {
    IntakeProgressScreen(modifier = Modifier
        .fillMaxWidth(0.5f)
        .background(
            waterColor, shape = RoundedCornerShape(16.dp)
        ), onAvgWaterIntake = "Avg. Water Intake", onAvgWaterPercent = "1600")
}