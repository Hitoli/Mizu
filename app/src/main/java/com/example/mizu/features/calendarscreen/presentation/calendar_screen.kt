package com.example.mizu.features.calendarscreen.presentation

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.mizu.ui.theme.backgroundColor1
import com.example.mizu.ui.theme.backgroundColor2
import com.example.mizu.ui.theme.fontFamilyLight
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.waterColor
import com.example.mizu.utils.Todos
import com.example.mizu.utils.calendar_utils.WaterGoals
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CalendarScreen(modifier: Modifier = Modifier, onMonth:String, listOfTodos:List<WaterGoals>,onPad:PaddingValues,caledarList:MutableList<List<Color>>,getSelected: (Int) -> Unit) {


    Box(modifier = modifier.padding(
        top = onPad.calculateTopPadding()/8)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.align(
            Alignment.Center)) {
            Text(
                text = "Your Monthly Report",
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(400),
                    color = minorColor,
                    textAlign = TextAlign.Start,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .background(
                        minorColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.35f)
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                ) {
                    Text(
                        text = onMonth,
                        modifier=Modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontFamilyLight,
                            fontWeight = FontWeight(400),
                            color = backgroundColor1,
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                    LazyColumn(modifier = Modifier,userScrollEnabled = false, content = {
                        items(caledarList.size) { row ->
                            Spacer(modifier = Modifier.size(4.dp))


                            LazyRow(modifier=Modifier, userScrollEnabled = false) {
                                items(caledarList.get(row)){it->

                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    it,
                                                    shape = RoundedCornerShape(6.dp)
                                                )
                                                .height(30.dp)
                                                .width(30.dp)
                                                .border(
                                                    width = 1.dp,
                                                    color = if(it!= waterColor)Color.White else minorColor,
                                                    shape = RoundedCornerShape(6.dp)
                                                )
                                        )




                                    Spacer(modifier = Modifier.size(4.dp))
                                }
                            }

                        }
                    })

                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Water Goals",
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(400),
                    color = minorColor,
                    textAlign = TextAlign.Start,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .background(
                        minorColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.45f)
                    .padding(16.dp)
            ) {
                LazyColumn(content = {
                   items(listOfTodos.size){
                       TodoTextsLayout(text = listOfTodos.get(it).goal, onSelected =listOfTodos.get(it).onSelected,getSelected={index->
                           getSelected(index)
                       }, index =it)
                   }
                })

            }
        }




    }
}

@Composable
fun TodoTextsLayout(text:String, onSelected:Boolean, getSelected:(Int)->Unit,index:Int) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = onSelected, onClick = {
                                                     getSelected(index)
        }, colors = RadioButtonDefaults.colors(
            selectedColor = waterColor, unselectedColor = backgroundColor1))
        Box(modifier=Modifier) {
            if(onSelected){
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height
                    println(text.length)
                    drawLine(
                        start = Offset(x = 0.dp.toPx(), y = canvasHeight / 2),
                        end = Offset(x = canvasWidth, y = canvasHeight / 2),
                        color = backgroundColor1,
                        strokeWidth = 1.dp.toPx() // instead of 5.dp.toPx() , you can also pass 5f
                    )
                }
            }

            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(start = 8.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontFamilyLight,
                    fontWeight = FontWeight(400),
                    color = backgroundColor1,
                    textAlign = TextAlign.Start,
                )
            )
        }
    }


}

@Composable
fun GraphScreen() {
    val pointsData: List<Point> =
        listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))
    val steps = 5
    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .topPadding(105.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> pointsData[i].x.toInt().toString() }
        .labelAndAxisLinePadding(15.dp).backgroundColor(backgroundColor2)
        .build()
    val yAxisData = AxisData.Builder().axisStepSize(100.dp)
        .steps(steps)
        .labelAndAxisLinePadding(20.dp).backgroundColor(backgroundColor2)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }.build()
    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(color = minorColor),
                    IntersectionPoint(color = waterColor),
                    SelectionHighlightPoint(minorColor),
                    ShadowUnderLine(minorColor),
                    SelectionHighlightPopUp(minorColor)
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = backgroundColor2
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        lineChartData = data
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendarScreen() {
    var todosList:MutableList<WaterGoals> = mutableListOf<WaterGoals>(

        WaterGoals(goal = "Keep a bottle by your desk",onSelected = false),
        WaterGoals(goal = "Keep a bottle by your desk",onSelected = false),
        WaterGoals(goal = "Keep a bottle by your desk",onSelected = false)


    )
    CalendarScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                    colors = listOf(backgroundColor1, backgroundColor2)
                )
            ), onMonth = "Feb", todosList, onPad = PaddingValues(40.dp), caledarList = mutableListOf(
            listOf(Color.Black)
        )
    , getSelected = {})
}