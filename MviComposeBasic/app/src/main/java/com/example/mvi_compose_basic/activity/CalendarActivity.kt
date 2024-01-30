package com.example.mvi_compose_basic.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvi_compose_basic.ui.theme.MviComposeBasicTheme
import java.time.LocalDate
import java.time.YearMonth

class CalendarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting4("Android")
                }
            }
        }
    }
}

@Composable
fun CalendarGrid(selectedDate: LocalDate, onDateClick: (LocalDate) -> Unit) {
    val daysOfWeek = listOf("월", "화", "수", "목", "금", "토", "일")
    val daysOfMonth = getDaysOfMonth(selectedDate)
    val firstDayOfMonth = selectedDate.withDayOfMonth(1) //매월 첫 날 1일
    val startingPos = daysOfWeek.indexOf(firstDayOfMonth.dayOfMonth.toString())

    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(7)) {
        items(daysOfWeek.size) { index ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primaryVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = daysOfWeek[index],
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
            }
        }

        items(daysOfMonth) { day ->
            val date = firstDayOfMonth.plusDays((day - 1).toLong())
            val isSelected = date == selectedDate

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = if (isSelected) Color.Magenta else Color.Transparent
                    )
                    .clickable {
                        onDateClick(date)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day.toString(),
                    style = MaterialTheme.typography.body1,
                    color = if (isSelected) Color.Blue else MaterialTheme.colors.primary
                )
            }
        }
    }
}

fun getDaysOfMonth(date: LocalDate): Int = YearMonth.from(date).lengthOfMonth()

@Composable
fun Greeting4(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    MviComposeBasicTheme {
        Greeting4("Android")
    }
}