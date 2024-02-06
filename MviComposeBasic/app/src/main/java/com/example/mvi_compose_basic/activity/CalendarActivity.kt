package com.example.mvi_compose_basic.activity

import android.os.Bundle
import android.widget.CalendarView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageResult
import com.example.mvi_compose_basic.ui.theme.MviComposeBasicTheme
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun CalendarScreen() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CalendarHeader(selectedDate = selectedDate, onDateChanged = {
            selectedDate = it
        })
        Spacer(modifier = Modifier.height(16.dp))
        CalendarGrid(selectedDate = selectedDate, onDateClick = {
            selectedDate = it
        })
    }
}

@Composable
fun CalendarHeader(selectedDate: LocalDate, onDateChanged: (LocalDate) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { onDateChanged.invoke(selectedDate.minusMonths(1)) }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Previous Month")
        }
        Text(
            text = selectedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
            style = MaterialTheme.typography.h6
        )
        IconButton(onClick = { onDateChanged(selectedDate.plusMonths(1)) }) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next Month")
        }
    }
}

@Composable
fun CalendarGrid(selectedDate: LocalDate, onDateClick: (LocalDate) -> Unit) {
    val daysOfWeek = listOf("월", "화", "수", "목", "금", "토", "일")
    val daysOfMonth = getDaysOfMonth(selectedDate)
    val firstDayOfMonth = selectedDate.withDayOfMonth(1) //매월 첫 날 1일
    val startingPos = daysOfWeek.indexOf(firstDayOfMonth.dayOfMonth.toString())

    AndroidView(factory = { CalendarView(it) })

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
                    }, contentAlignment = Alignment.Center
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
fun CustomDialog(
    title: String, subtitle: String, onConfirm: () -> Unit, onCancel: () -> Unit
) {
    var isOpen by remember { mutableStateOf(true) }

    if (isOpen) {
        Dialog(
            onDismissRequest = { isOpen = false }, properties = DialogProperties(
                dismissOnBackPress = false, dismissOnClickOutside = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = title, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = subtitle, style = MaterialTheme.typography.body2)
                    Row(
                        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = {
                            onConfirm.invoke()
                            isOpen = false
                        }, modifier = Modifier.padding(8.dp)) {
                            Text(text = "확인")
                        }
                        Button(onClick = {
                            onConfirm.invoke()
                            isOpen = false
                        }, modifier = Modifier.padding(8.dp)) {
                            Text(text = "취소")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var isShowDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = { isShowDialog = true }) {
            Text(text = "다이얼로그 표시")
        }
        CoilTestImage()
    }

    if (isShowDialog) {
        CustomDialog(title = "테스트 제목", subtitle = "테스트 부제목", onConfirm = {

        }, onCancel = {

        })
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoilTestImage() {
    val imageUrl = "https://s3.orbi.kr/data/file/united2/4f83f6b1982f49048bdcf9b1761b04da.jpg"

    val painter = rememberImagePainter(data = imageUrl, builder = {
        //원하는 이미지 설정 추가
    })

    Image(
        painter = painter, contentDescription = null,
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp)
    )

    when (painter.state) {
        is ImagePainter.State.Loading -> {
            Text(text = "로딩 중....", color = Color.Gray)
        }
        is ImagePainter.State.Error -> {
            Text(text = "로딩 실패!", color = Color.Red)
        }
        else -> {}
    }
}

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