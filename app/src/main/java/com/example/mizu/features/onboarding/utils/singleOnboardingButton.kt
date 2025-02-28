package com.example.mizu.features.onboarding.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.waterColor

@Composable
fun SingleButton(modifier: Modifier = Modifier, getNavigate:()->Unit, buttonName:String) {
    Box(
        modifier = Modifier
            .clickable(interactionSource = remember {
                MutableInteractionSource()
            }, indication = null, onClick = {
                getNavigate()
            })
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .background(waterColor, shape = RoundedCornerShape(6.dp)),
    ) {
        Text(
            text = buttonName,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = fontFamilyLight,
                fontWeight = FontWeight(400),
                color = mizuBlack,
                textAlign = TextAlign.Center,
            ), modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSingleButton(){
    SingleButton(buttonName = "Next", getNavigate = {})
}