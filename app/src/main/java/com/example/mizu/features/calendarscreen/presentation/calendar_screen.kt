package com.example.mizu.features.calendarscreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.mizu.ui.theme.minorColor
import com.example.mizu.ui.theme.waterColor

@Composable
fun CalendarScreen(modifier:Modifier=Modifier) {

    Box(modifier = modifier){

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .background(
                    minorColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth(0.9f).fillMaxHeight(0.18f)
                .padding(16.dp)
        ){
            LazyHorizontalGrid(rows =GridCells.Fixed(6) , content ={
                items(96){i->
                    Box(modifier = Modifier.background(waterColor).height(20.dp).width(20.dp).border(width = 1.dp, color = minorColor))
                }
            } )

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
    CalendarScreen(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
                colors = listOf(backgroundColor1, backgroundColor2)
            )
        ))
}