package com.example.mizu.presentation_app.navmap

import ProfileScreen
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mizu.R
import com.example.mizu.features.calendarscreen.presentation.CalendarScreen
import com.example.mizu.features.homescreen.presentation.HomeScreen
import com.example.mizu.features.profilescreen.utils.ProfileData
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.mizuBlack
import com.example.mizu.ui.theme.waterColor
import com.example.mizu.presentation_app.navmap.nav_utils.BottomNavScreens
import com.example.mizu.utils.calendar_utils.WaterGoals
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun BottomBarHostingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onWaterTrackingResourceAmount: Int,
    getWaterTrackingResourceAmount: (Int) -> Unit,
    onTotalWaterTrackingResourceAmount: Int,
    getUpdateTotalWaterTrackingAmount: (Int) -> Unit,
    getAddWater: () -> Unit,
    onUserName: String,
    getReward: (Boolean?) -> Unit,
    onReward: Boolean?,
    onWaterMeterResourceAmount: Int,
    onStreak: String,
    getStreak: () -> Unit, onProgress: String,
    onTime: String,
    getGreeting: () -> Unit,
    isEndless: Boolean = true,
    items: List<Int>,
    streakImages: List<Int>,
    onMonth: String,
    calendarList: MutableList<List<Color>>,
    onWaterGoals: List<WaterGoals>,
    getSelected: (Int) -> Unit,
    getProfileClick: () -> Unit,
    imgModifier: Modifier,
    onAvgIntake: String, onHeight: String, onBestStreak: String, onWeight: String
) {
    var onAdd by remember {
        mutableStateOf(false)
    }
    var onWaterAddSheet by remember {
        mutableStateOf(false)
    }
    var onHome by remember {
        mutableStateOf(true)
    }
    var onTitleChage by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableIntStateOf(-1)
    }
    var showBottomBar by remember {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true);
    val navItems = mutableListOf(
        BottomNavScreens.HomeScreen,
        BottomNavScreens.CalendarScreen,
        BottomNavScreens.ProfileScreen
    )
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        Log.d(
            "onTotalWaterTrackingResourceAmount Onboarding",
            onTotalWaterTrackingResourceAmount.toString()
        )
        getUpdateTotalWaterTrackingAmount(onTotalWaterTrackingResourceAmount)
        getGreeting()
        delay(3000)
        onTitleChage = true
        delay(3000)
        onTitleChage = false
    }
//    LaunchedEffect(key1 = listState) {
//        snapshotFlow{
//            listState.firstVisibleItemIndex
//        }.collect{
//            if (it>items.size-1){
//                println("Selected Index => Greater than Item list")
//                selected = (it%(items.size-1))
//            }else{
//                println("Selected Index => Smaller than Item list")
//                selected = it+1
//                if(selected==6){
//                    selected =0
//                }
//
//            }
//            println("Selected Index => ${selected}")
//
//        }
//
//
//    }


    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarLayout(
                onTime = onTime,
                getNotificationClick = {},
                getPorfileClick = {
                    getProfileClick()
                },
                onUserName = onUserName,
                onTitleChange = onTitleChage,
                imgModifier = imgModifier
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
                    onClick = {
                        onWaterAddSheet = !onWaterAddSheet
                    },
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
                    AnimatedVisibility(visible = !onAdd, enter = fadeIn(), exit = fadeOut()) {
                        Box(
                            modifier = Modifier
                                .background(color = mizuBlack, shape = RoundedCornerShape(16.dp))
                                .size(width = 100.dp, height = 60.dp)
                        ) {
                            Text(
                                text = "Add", modifier = Modifier
                                    .align(
                                        Alignment.Center
                                    ),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = fontFamilyLight,
                                    fontWeight = FontWeight(400),
                                    color = backgroundColor1,
                                    textAlign = TextAlign.Center,
                                )

                            )
                        }
                    }
                }
            }


        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
//        SharedTransitionScope {
//
//        }
        val padding = it
        NavHost(
            navController = navController,
            startDestination = BottomNavScreens.HomeScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )
            }
        ) {


            composable(route = BottomNavScreens.HomeScreen.route) {
                HomeScreen(
                    onPad = padding,
                    onWaterTrackingResourceAmount = onWaterTrackingResourceAmount,
                    onTotalWaterTrackingResourceAmount = onTotalWaterTrackingResourceAmount,
                    getReward = getReward,
                    onReward = onReward,
                    getBottomBar = {
                        showBottomBar = it
                    },
                    onWaterMeterResourceAmount = onWaterMeterResourceAmount,
                    onProgress = onProgress,
                    onStreak = onStreak,
                    streakImages = streakImages,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                start = Offset(Float.POSITIVE_INFINITY, 0f),
                                end = Offset(0f, Float.POSITIVE_INFINITY),
                                colors = mutableListOf(backgroundColor1, backgroundColor2)
                            )
                        )
                )
            }
            composable(route = BottomNavScreens.CalendarScreen.route) {
                CalendarScreen(
                    onMonth = onMonth,
                    listOfTodos = onWaterGoals,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                start = Offset(Float.POSITIVE_INFINITY, 0f),
                                end = Offset(0f, Float.POSITIVE_INFINITY),
                                colors = mutableListOf(backgroundColor1, backgroundColor2)
                            )
                        ),
                    onPad = padding,
                    caledarList = calendarList,
                    getSelected = {
                        getSelected(it)
                    },
                    onHeight = onHeight,
                    onBestStreak = onBestStreak,
                    onWeight = onWeight,
                    onAvgIntake = onAvgIntake,
                    intakeAmount = if (onWaterTrackingResourceAmount >= onTotalWaterTrackingResourceAmount) {
                        onTotalWaterTrackingResourceAmount.toString()
                    } else {
                        onWaterTrackingResourceAmount.toString()
                    }
                )
            }
            composable(route = BottomNavScreens.ProfileScreen.route) {
                ProfileScreen(
                    getBack = { /*TODO*/ },
                    profileData = ProfileData(
                        onNotificationChange = false,
                        onNameError = "",
                        onNameCheck = false,
                        onNameChange = "",
                        onEmailCheck = false,
                        onEmailChange = "",
                        onEmailError = ""
                    ),
                    getNameChange = {},
                    getNavigate = { /*TODO*/ },
                    getEmailChange = {},
                    getNotificationChange = {},
                    getNotificationIntervals = { /*TODO*/ }, getBugReport = {}, modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                start = Offset(Float.POSITIVE_INFINITY, 0f),
                                end = Offset(0f, Float.POSITIVE_INFINITY),
                                colors = mutableListOf(backgroundColor1, backgroundColor2)
                            )
                        )
                        .padding(
                            bottom = padding.calculateBottomPadding() / 4,
                            top = padding.calculateTopPadding() / 8
                        )
                )
            }
        }
    }
    if (onWaterAddSheet) {
//        ModalBottomSheet(
//            containerColor = backgroundColor1,
//            sheetState = sheetState,
//            onDismissRequest = { onWaterAddSheet = !onWaterAddSheet }, modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.65f)
//                .padding(10.dp)
//        ) {
        Dialog(properties = DialogProperties(
            usePlatformDefaultWidth = true,
            decorFitsSystemWindows = true
        ),
            onDismissRequest = { onWaterAddSheet = !onWaterAddSheet }) {
            WaterCarouselSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f)
                    .background(
                        backgroundColor1.copy(alpha = 0.9f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                listState,
                isEndless,
                items,
                selected,
                getWaterTrackingResourceAmount,
                getSelected = {
                    selected = it
                },
                getWaterAddSheet = {
                    onWaterAddSheet = it
                })

        }
    }
}

@Composable
fun WaterCarouselSheet(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    isEndless: Boolean,
    items: List<Int>,
    selected: Int,
    getWaterTrackingResourceAmount: (Int) -> Unit,
    getSelected: (Int) -> Unit,
    getWaterAddSheet: (Boolean) -> Unit
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Add Water: ", modifier = Modifier.weight(0.5f),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.Center,
                    )

                )
                Text(
                    text = if (selected != -1) items[selected].toString() else "",
                    modifier = Modifier.weight(0.5f),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.Center,
                    )

                )
            }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .height(250.dp)

            ) {
                items(
                    count = if (isEndless) Int.MAX_VALUE else items.size,
                    itemContent = {
                        val index = it % items.size
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = items[index].toString(), modifier = Modifier
                                    .align(
                                        Alignment.Center
                                    )
                                    .clickable {
                                        getSelected(index)
                                        Log.d("Target Water ", items[index].toString())
                                    },
                                style = TextStyle(
                                    fontSize = if (index == selected) 40.sp else 30.sp,
                                    fontFamily = fontFamilyLight,
                                    fontWeight = if (index == selected) FontWeight(500) else FontWeight(
                                        400
                                    ),
                                    color = if (index == selected) mizuBlack else mizuBlack.copy(
                                        alpha = 0.6f
                                    ),
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (selected != -1 && selected < items.size) {
                        getWaterTrackingResourceAmount(items[selected])
                        getWaterAddSheet(false)
                        getSelected(-1)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 14.dp, end = 14.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonColors(
                    containerColor = waterColor,
                    contentColor = mizuBlack,
                    disabledContainerColor = waterColor,
                    disabledContentColor = mizuBlack
                )
            ) {
                Text(
                    text = "Done", modifier = Modifier,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = fontFamilyLight,
                        fontWeight = FontWeight(400),
                        color = mizuBlack,
                        textAlign = TextAlign.Center,
                    )

                )
            }
            Spacer(modifier = Modifier.height(10.dp))
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
    onUserName: String,
    imgModifier: Modifier
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
                        color = mizuBlack,
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
                        color = mizuBlack,
                        textAlign = TextAlign.Start,
                    )
                )
            }

        },
        actions = {
//            Row(
//                modifier = Modifier,
//                horizontalArrangement = Arrangement.Start,
//                verticalAlignment = Alignment.Top
//            ) {
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        imageVector = ImageVector.vectorResource(R.drawable.notificationmizu),
//                        contentDescription = "notification",
//                        tint = minorColor
//                    )
//                }
//                IconButton(onClick = {getPorfileClick() }) {
//                    Image(
//                        modifier = imgModifier,
//                        painter = painterResource(id = R.drawable.mizunamelogo),
//                        contentDescription = "UserIcon"
//                    )
//                }
//            }

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
    val navBackStackEntry by navController.currentBackStackEntryAsState();
    val currentRoute = navBackStackEntry?.destination?.route
    BottomAppBar(
        modifier = Modifier
            .height(80.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 8.dp, topEnd = 8.dp
                )
            )
            .border(width = 0.5.dp, color = mizuBlack, shape = RoundedCornerShape(8.dp)),
        containerColor = mizuBlack
    ) {
        NavigationBar(containerColor = mizuBlack) {
            navScreens.fastForEachIndexed { _, bottomData ->
                NavigationBarItem(
                    selected = bottomData.route == currentRoute,
                    onClick = {
                        navController.navigate(bottomData.route) {
                            when (bottomData.route) {
                                "Track" -> {
                                    getHome(false)
                                }
                                "Profile" -> {
                                    getHome(false)
                                }
                                else -> {
                                    getHome(true)
                                }
                            }
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 1.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Log.d("Navigation", bottomData.route)
                            Icon(
                                imageVector = ImageVector.vectorResource(bottomData.icon),
                                contentDescription = bottomData.route,
                                tint = if (currentRoute == bottomData.route) mizuBlack else backgroundColor1,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            AnimatedVisibility(
                                visible = bottomData.route == currentRoute,
                                enter = expandHorizontally(),
                                exit = shrinkHorizontally()
                            ) {
                                Text(
                                    text = bottomData.route,
                                    modifier = Modifier,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = fontFamilyLight,
                                        fontWeight = FontWeight(200),
                                        color = mizuBlack,
                                        textAlign = TextAlign.Start,
                                    )
                                )
                            }


                        }

                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        mizuBlack, indicatorColor = waterColor
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
                    colors = mutableListOf(backgroundColor1, backgroundColor2)
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
        items = mutableListOf(50, 100, 200, 300, 400, 500),
        getUpdateTotalWaterTrackingAmount = {},
        streakImages = mutableListOf(R.drawable.day2, R.drawable.day1),
        onMonth = "",
        onWaterGoals = mutableListOf(),
        calendarList = mutableListOf(
            mutableListOf(Color.Black)
        ),
        getSelected = {},
        getProfileClick = {},
        imgModifier = Modifier,
        onAvgIntake = "1700ml",
        onWeight = "72",
        onHeight = "172",
        onBestStreak = "10"
    )
}