package com.example.composestudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composestudy.ui.theme.ComposeStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                // A surface container using the 'background' color from the theme
                ButtonExample {
                    Toast.makeText(this, "Send Clicked!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column {
        Text(
            color = Color.Red,
            text = "Hello $name!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        Text(
            color = Color.Red,
            text = "Hello $name!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            letterSpacing = 2.sp,
            maxLines = 2,
            textDecoration = TextDecoration.Underline
        )
        Text(
            modifier = Modifier.width(150.dp),
            color = Color.Red,
            text = "Hello $name!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            letterSpacing = 2.sp,
            maxLines = 2,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SurfaceExample(name: String) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "Hello, $name!",
            modifier = Modifier.padding(8.dp)
        )
    }

    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.padding(5.dp),
        elevation = 10.dp,
        border = BorderStroke(
            width = 2.dp,
            color = Color.Magenta
        ),
        shape = CircleShape,
    ) {
        Text(
            text = "Hello, $name!",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ButtonExample(onClicked: () -> Unit) {
    Button(
        onClick = { onClicked.invoke() },
        enabled = false
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Send")
    }
    Button(
        onClick = { onClicked.invoke() },
        enabled = true,
        border = BorderStroke(10.dp, Color.Magenta),
        shape = CircleShape,
        contentPadding = PaddingValues(20.dp) //내부 내용 가장자리
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Send")
    }
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
//            .fillMaxSize()
            .height(200.dp)
            .width(200.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Search")
    }

    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Magenta,
            contentColor = Color.Cyan
        ),
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(10.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier.background(Color.Blue)
        )
        Spacer(
            modifier = Modifier
                .size(ButtonDefaults.IconSpacing)
                .background(Color.Blue)
        )
        Text(text = "Search",
            modifier = Modifier
                .clickable { // 클릭 가능한 오브젝트 설정
                }
                .offset(y = 10.dp)
                .background(Color.Blue))
    }
}

@Composable
fun BoxEx() {
    Box(modifier = Modifier.size(100.dp)) {
        Text(text = "Hello!", modifier = Modifier.align(Alignment.BottomEnd))
        Text(text = "Hello!", modifier = Modifier.align(Alignment.CenterEnd))
    }

    Box {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan)
                .align(Alignment.CenterStart)
        )
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.Cyan)
                .align(Alignment.CenterStart)
        )
    }
}

@Composable
fun RowEx() {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.height(40.dp)
    ) {
        Text(text = "첫 번째", modifier = Modifier.align(Alignment.Top)) //이 부분만 상단 배치
        Text(text = "두 번째")
        Text(text = "세 번째")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStudyTheme {
        Greeting("Android")
    }
}