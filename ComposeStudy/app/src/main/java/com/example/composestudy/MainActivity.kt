package com.example.composestudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
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
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStudyTheme {
        Greeting("Android")
    }
}