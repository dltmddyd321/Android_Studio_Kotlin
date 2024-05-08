package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.greenrobot.eventbus.EventBus

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().post(MessageEvent("Hello, EventBus!"))

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun TbLogo(
    isDarkMode: Boolean,
    logoIcon: Painter,
    logoSize: Dp,
    labelSize: TextUnit,
    labelWeight: FontWeight
) {
    Box(
        modifier = Modifier
            .background(
                if (isDarkMode) Color.Black else Color.White, shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = logoIcon,
                contentDescription = "Logo",
                modifier = Modifier.size(logoSize)
            )
            TbText(
                modifier = Modifier.padding(start = 8.dp),
                text = "TimeBlocks",
                isDarkMode = isDarkMode,
                labelSize = labelSize,
                labelWeight = labelWeight
            )
        }
    }
}

@Composable
fun TbText(
    modifier: Modifier,
    text: String,
    isDarkMode: Boolean,
    labelSize: TextUnit,
    labelWeight: FontWeight,
    labelAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        color = if (isDarkMode) Color.White else Color.Black,
        fontSize = labelSize,
        fontWeight = labelWeight,
        textAlign = labelAlign,
        modifier = modifier
    )
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        color = Color.Cyan
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("승용")
    }
}