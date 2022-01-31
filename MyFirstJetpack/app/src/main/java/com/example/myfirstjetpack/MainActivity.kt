package com.example.myfirstjetpack

import android.os.Bundle
import android.os.TestLooperManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstjetpack.ui.theme.MyFirstJetpackTheme
import com.example.myfirstjetpack.ui.theme.Purple200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MyApp{
                    MyScreenContent()
                }
            }

        Toast.makeText(this, "Welcome to Jetpack Compose!!", Toast.LENGTH_SHORT).show()
        }
    }

@Composable
fun Greeting(name: String) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    val backgroundColor by animateColorAsState(
        if(isSelected) Color.Red else Color.Transparent
    )

    Text(text = "안녕하세요! $name",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = {isSelected = !isSelected}))
//    Text(text = "Hello $name!",
//        modifier = Modifier.padding(24.dp),
//        color = Color.White)
}

@Composable
fun MyScreenContent(names: List<String> = List(100){"Test $it"}) {
    val cntState = remember {
        mutableStateOf(0)
    }
    
    Column(modifier = Modifier.fillMaxHeight()) {
//        Column(modifier = Modifier.weight(1f)) {
//            for(name in names){
//                Greeting(name = name)
//                Divider(color = Color.Red)
//            }
//        }
        NameList(names, Modifier.weight(1f))
        Counter(
            cnt = cntState.value,
            updateCnt = { newCnt ->
                cntState.value = newCnt
            }
        )
    }
}

@Composable
fun MyApp(content : @Composable () -> Unit) {
    MyFirstJetpackTheme {
        Surface(color = Color.Blue) {
            content()
        }
    }
}

@Composable
fun Counter(cnt : Int, updateCnt : (Int) -> Unit) {
    Button(
        onClick = {updateCnt(cnt + 1)},
        colors = ButtonDefaults.buttonColors(
        backgroundColor = if(cnt>5) Color.Cyan else Color.Blue)
    ) {
        Text("${cnt}번 클릭!!")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        MyScreenContent()
    }
}