package com.example.mizu.presentation_app.navmap

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mizu.R
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor

@Composable
fun BottomBarHostingScreen(modifier:Modifier=Modifier,navController:NavHostController= rememberNavController()) {
    Scaffold(modifier = modifier,
        topBar = {
            TopBarLayout(onTime = "GoodMorning", getNotificationClick = {}, getPorfileClick = {})
        }, bottomBar = {
            BottomBarLayout(navController)
        }
    ) {
        val padding = it
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLayout(onTime:String, getNotificationClick:()->Unit, getPorfileClick:()->Unit) {
    TopAppBar(title = { Text(
        text = "Good Morning \uD83D\uDC4B",
        modifier= Modifier
            .fillMaxWidth()
            .padding(start = 0.dp),
        style = TextStyle(
            fontSize = 26.sp,
            fontFamily = fontFamilyLight,
            fontWeight = FontWeight(200),
            color = minorColor,
            textAlign = TextAlign.Start,
        )
    )}, actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector =ImageVector.vectorResource(R.drawable.notificationmizu) , contentDescription ="notification")
        }
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Image(painter = painterResource(id = R.drawable.mizunamelogo), contentDescription ="UserIcon" )
        }
    })
}

@Composable
fun BottomBarLayout(navController: NavHostController) {

    BottomAppBar(modifier = Modifier
        .padding(16.dp)
        .clip(
            shape = RoundedCornerShape(
                20.dp
            )
        )
        .border(width = 1.dp, color = minorColor, shape = RoundedCornerShape(20.dp))){
        NavigationBar(containerColor = minorColor) {

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBottomBarHostingScreen() {
    val navController = rememberNavController()

    BottomBarHostingScreen(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
                colors = listOf(backgroundColor1, backgroundColor2)
            )
        ), navController = navController)
}