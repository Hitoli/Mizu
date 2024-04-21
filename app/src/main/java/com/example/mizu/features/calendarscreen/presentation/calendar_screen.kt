package com.example.mizu.features.calendarscreen.presentation

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

@Composable
fun CalendarScreen(modifier: Modifier = Modifier, onMonth:String, listOfTodos: List<Todos>,onPad:PaddingValues) {

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


                    LazyHorizontalGrid(modifier = Modifier, rows = GridCells.Fixed(6), content = {
                        items(35) { i ->
                            Box(
                                modifier = Modifier
                                    .background(waterColor, shape = RoundedCornerShape(6.dp))
                                    .height(45.dp)
                                    .width(45.dp)
                                    .border(
                                        width = 1.dp,
                                        color = minorColor,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                            )
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
                    .fillMaxHeight(0.40f)
                    .padding(16.dp)
            ) {
                LazyColumn(content = {
                   items(listOfTodos){
                       TodoTextsLayout(text = it.text, onSelected =it.onSelected, getSelected =it.getSelected)
                   }
                })

            }
        }




    }
}

@Composable
fun TodoTextsLayout(text:String, onSelected:Boolean, getSelected:(Boolean)->Unit) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = onSelected, onClick = {
                                                     getSelected(!onSelected)
        }, colors = RadioButtonDefaults.colors(
            selectedColor = backgroundColor1, unselectedColor = backgroundColor1))
        Box(modifier=Modifier) {
            Canvas(
                modifier = Modifier.fillMaxSize().align(Alignment.Center)
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
    var todosList:MutableList<Todos> = mutableListOf<Todos>(
        Todos(text = "Keep a bottle by your desk", onSelected = false, getSelected = {}),
        Todos(text = "Keep a bottle by your desk", onSelected = false, getSelected = {}),
        Todos(text = "Keep a bottle by your desk", onSelected = true, getSelected = {})


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
            ), onMonth = "Feb", todosList, onPad = PaddingValues(40.dp)
    )
}