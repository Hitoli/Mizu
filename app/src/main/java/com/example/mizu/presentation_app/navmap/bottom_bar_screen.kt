package com.example.mizu.presentation_app.navmap

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.mizu.R
import com.example.mizu.features.calendarscreen.presentation.CalendarScreen
import com.example.mizu.features.homescreen.presentation.HomeScreen
import com.example.mizu.features.homescreen.view_model.HomeViewModel
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.waterColor
import com.example.mizu.utils.BottomNavScreens
import com.example.mizu.utils.Todos
import kotlinx.coroutines.delay

@Composable
fun BottomBarHostingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onWaterTrackingResourceAmount: Int,
    getWaterTrackingResourceAmount: (Int) -> Unit,
    onTotalWaterTrackingResourceAmount: Int,
    getAddWater: () -> Unit,
    onUserName: String,
    getReward: (Boolean?) -> Unit,
    onReward: Boolean?,
    onWaterMeterResourceAmount: Int,
    onStreak: String,
    getStreak: () -> Unit, onProgress: String,
    onTime: String,
    getGreeting: () -> Unit,
    isEndless: Boolean = false,
    items: List<Int>,


    ) {
    var todosList: MutableList<Todos> = mutableListOf<Todos>(
        Todos(text = "Keep a bottle by your desk", onSelected = false, getSelected = {}),
        Todos(text = "Keep a bottle by your desk", onSelected = false, getSelected = {}),
        Todos(text = "Keep a bottle by your desk", onSelected = true, getSelected = {})


    )
    var onAdd by remember{
        mutableStateOf(false)
    }
    var onHome by remember {
        mutableStateOf(true)
    }
    var onTitleChage by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        getGreeting()
        delay(3000)
        onTitleChage = true
        delay(3000)
        onTitleChage = false

    }
    var selected by remember{
        mutableIntStateOf(0)
    }
    var showBottomBar by remember {
        mutableStateOf(false)
    }
    val navItems = listOf<BottomNavScreens>(
        BottomNavScreens.HomeScreen,
        BottomNavScreens.CalendarScreen
    )
    val listState = rememberLazyListState(
        if (isEndless) Int.MAX_VALUE / 2 else 0
    )


    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarLayout(
                onTime = onTime,
                getNotificationClick = {},
                getPorfileClick = {},
                onUserName = onUserName,
                onTitleChange = onTitleChage
            )
        },
        bottomBar = {
            Log.d("SCROLL", showBottomBar.toString());
            AnimatedVisibility(visible = !showBottomBar, enter = fadeIn(), exit = fadeOut()) {
                BottomBarLayout(navController, navScreens = navItems, getHome = {
                    onHome = it
                })
            }

        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = !showBottomBar && onHome,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                ExtendedFloatingActionButton(
                    onClick = {},
                    containerColor = Color.Transparent,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        focusedElevation = 0.dp,
                        hoveredElevation = 0.dp
                    ),
                    contentColor = Color.White,
                    modifier = Modifier.background(Color.Transparent),
                ) {
//                    AnimatedVisibility(visible = !onAdd, enter = fadeIn(), exit = fadeOut()) {
//                        Box(modifier = Modifier
//                            .background(color = minorColor, shape = RoundedCornerShape(16.dp))
//                            .size(width = 100.dp, height = 60.dp)){
//                            Text(
//                                text = "Add", modifier = Modifier
//                                    .align(
//                                        Alignment.Center
//                                    )
//                                    .clickable {
//                                        onAdd = !onAdd
//                                    }
//                            )
//                        }
//                    }


//                    AnimatedVisibility(visible = onAdd, enter = fadeIn(), exit = fadeOut()) {
                        LazyRow(
                            state = listState,
                            modifier = Modifier.background(Color.Transparent)
                        ) {
                            items(
                                count = if (isEndless) Int.MAX_VALUE else items.size,
                                itemContent = {
                                    val index = it % items.size
                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .background(
                                                if (selected == index) minorColor else minorColor.copy(
                                                    alpha = 0.5f
                                                ), shape = RoundedCornerShape(50.dp)
                                            )
                                            .padding(16.dp)
                                    ) {
                                        Text(
                                            text = items[index].toString(), modifier = Modifier
                                                .align(
                                                    Alignment.Center
                                                )
                                                .clickable {
                                                    getWaterTrackingResourceAmount(items[index])
                                                    println("Target Water ${items[index]}")
                                                    selected = index
                                                }
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(100.dp))
                                }
                            )
                        }
//                    }


                }
            }


        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        val padding = it
        NavHost(
            navController = navController,
            startDestination = BottomNavScreens.HomeScreen.route
        ) {
            composable(route = BottomNavScreens.HomeScreen.route) {
                HomeScreen(
                    onPad = padding,
                    onWaterTrackingResourceAmount = onWaterTrackingResourceAmount,
                    getWaterTrackingResourceAmount = getWaterTrackingResourceAmount,
                    onTotalWaterTrackingResourceAmount = onTotalWaterTrackingResourceAmount,
                    getAddWater = getAddWater,
                    onUserName = onUserName,
                    getReward = getReward,
                    onReward = onReward,
                    getBottomBar = {
                        showBottomBar = it
                    },
                    onWaterMeterResourceAmount = onWaterMeterResourceAmount,
                    onProgress = onProgress,
                    onStreak = onStreak,
                    getStreak = getStreak
                )
            }
            composable(route = BottomNavScreens.CalendarScreen.route) {
                CalendarScreen(
                    onMonth = "Feb", listOfTodos = todosList, modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                start = Offset(Float.POSITIVE_INFINITY, 0f),
                                end = Offset(0f, Float.POSITIVE_INFINITY),
                                colors = listOf(backgroundColor1, backgroundColor2)
                            )
                        ), onPad = padding
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLayout(
    onTime: String,
    getNotificationClick: () -> Unit,
    getPorfileClick: () -> Unit,
    onTitleChange: Boolean,
    onUserName: String
) {

    TopAppBar(
        title = {
            AnimatedVisibility(
                visible = !onTitleChange, enter = fadeIn(), exit = fadeOut()
            ) {
                Text(
                    text = "${onTime} \uD83D\uDC4B",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(200),
                        color = minorColor,
                        textAlign = TextAlign.Start,
                    )
                )
            }
            AnimatedVisibility(
                visible = onTitleChange, enter = fadeIn(), exit = fadeOut()
            ) {
                Text(
                    text = "${onUserName} \uD83D\uDC4B",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(200),
                        color = minorColor,
                        textAlign = TextAlign.Start,
                    )
                )
            }

        },
        actions = {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.notificationmizu),
                        contentDescription = "notification",
                        tint = minorColor
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.mizunamelogo),
                        contentDescription = "UserIcon"
                    )
                }
            }

        },
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.Transparent,
            titleContentColor = Color.Transparent,
            actionIconContentColor = Color.Transparent
        ), modifier = Modifier
            .padding(top = 8.dp, bottom = 0.dp, start = 8.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
    )
}

@Composable
fun BottomBarLayout(
    navController: NavHostController,
    navScreens: List<BottomNavScreens>,
    getHome: (Boolean) -> Unit
) {
    val navBackStackentry by navController.currentBackStackEntryAsState();
    val currentRoute = navBackStackentry?.destination?.route
    BottomAppBar(
        modifier = Modifier
            .height(80.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 16.dp, topEnd = 16.dp
                )
            )
            .border(width = 1.dp, color = minorColor, shape = RoundedCornerShape(20.dp)),
        containerColor = minorColor
    ) {
        NavigationBar(containerColor = minorColor) {
            navScreens.forEachIndexed { index, bottomData ->
                NavigationBarItem(
                    selected = bottomData.route == currentRoute,
                    onClick = {
                        navController.navigate(bottomData.route) {
                            if (bottomData.route == "Track") {
                                getHome(false)
                            } else {
                                getHome(true)
                            }
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Icon(
                                imageVector = ImageVector.vectorResource(bottomData.icon),
                                contentDescription = bottomData.route,
                                tint = if (currentRoute == bottomData.route) minorColor else backgroundColor1
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            AnimatedVisibility(
                                visible = bottomData.route == currentRoute,
                                enter = expandHorizontally(),
                                exit = shrinkHorizontally()
                            ) {
                                Text(
                                    text = bottomData.route,
                                    modifier = Modifier,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = fontFamilyLight,
                                        fontWeight = FontWeight(200),
                                        color = minorColor,
                                        textAlign = TextAlign.Start,
                                    )
                                )
                            }


                        }

                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        minorColor, indicatorColor = waterColor
                    )
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBottomBarHostingScreen() {
    val navController = rememberNavController()

    BottomBarHostingScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(backgroundColor1, backgroundColor2)
                )
            ),
        navController = navController,
        onReward = false,
        onTotalWaterTrackingResourceAmount = 300,
        onUserName = "Hitesh",
        onWaterTrackingResourceAmount = 300,
        getAddWater = {},
        getReward = {},
        getWaterTrackingResourceAmount = {},
        onWaterMeterResourceAmount = 20,
        onStreak = "6",
        onProgress = "You are half way through keep it going",
        getStreak = {},
        onTime = "Goodmorning",
        getGreeting = {},
        items = listOf(50, 100, 200, 300, 400, 500)
    )
}