package com.example.composestudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.composestudy.ui.theme.ComposeStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                // A surface container using the 'background' color from the theme
                ImageEx()
            }
        }
    }

    companion object {
        val cardData = CardData(
            "https://i1.ruliweb.com/img/22/12/11/184ff52f60b567d03.jpg",
            "고토 히토리",
            "Anime",
            "봇치 더 락"
        )
    }
}

@Composable
fun CardEx(cardData: CardData) {
    val placeHolderColor = Color(0x33000000)

    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(model = cardData.imageUri, contentDescription = cardData.imageDescription)
            Spacer(modifier = Modifier.size(8.dp))
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
        color = MaterialTheme.colors.surface, modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "Hello, $name!", modifier = Modifier.padding(8.dp)
        )
    }

    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.padding(5.dp),
        elevation = 10.dp,
        border = BorderStroke(
            width = 2.dp, color = Color.Magenta
        ),
        shape = CircleShape,
    ) {
        Text(
            text = "Hello, $name!", modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ButtonExample(onClicked: () -> Unit) {
    Button(
        onClick = { onClicked.invoke() }, enabled = false
    ) {
        Icon(
            imageVector = Icons.Filled.Send, contentDescription = null
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
            imageVector = Icons.Filled.Send, contentDescription = null
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Send")
    }
    Button(
        onClick = { /*TODO*/ }, modifier = Modifier
//            .fillMaxSize()
            .height(200.dp)
            .width(200.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Search, contentDescription = null
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Search")
    }

    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Magenta, contentColor = Color.Cyan
        ), onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp)
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
        Text(text = "Search", modifier = Modifier
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

//가로
@Composable
fun RowEx() {
    Row(
        verticalAlignment = Alignment.Bottom, modifier = Modifier.height(40.dp)
    ) {
        Text(text = "첫 번째", modifier = Modifier.align(Alignment.Top)) //이 부분만 상단 배치
        Text(text = "두 번째")
        Text(text = "세 번째")
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround, //간격 공백
        verticalAlignment = Alignment.Bottom, modifier = Modifier
            .height(20.dp)
            .width(200.dp)
    ) {
        Text(
            text = "첫 번째", modifier = Modifier
                .align(Alignment.Top)
                .weight(3f)
        )
        Text(text = "두 번째", modifier = Modifier.weight(2f))
        Text(text = "세 번째", modifier = Modifier.weight(3f))
    }
}

//세로
@Composable
fun ColumnEx() {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.size(100.dp)
//    ) {
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.size(100.dp)
    ) {
        Text(text = "첫 번째", modifier = Modifier.align(Alignment.CenterHorizontally))
        Text(text = "두 번째")
        Text(text = "세 번째", modifier = Modifier.align(Alignment.Start))
    }
}

@Composable
fun ImageEx() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "테스트 이미지"
        )
        Image(imageVector = Icons.Filled.Settings, contentDescription = "설정")
        AsyncImage(
            model = "https://i1.ruliweb.com/img/22/12/11/184ff52f60b567d03.jpg",
            contentDescription = "고토 히토리"
        )
    }
}

@Composable
fun TextFieldEx() {
    var name by remember { mutableStateOf("Tom") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = name, label = { Text(text = "이름") }, onValueChange = {
            name = it
        })

        Spacer(modifier = Modifier.size(8.dp))

        Text(text = "Hello, $name!")
    }
}

@Composable
fun TopAppBarEx(name: String) {
    Column {
        TopAppBar(
            title = { Text(text = "TopAppBar") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
            }, actions = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "검색")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "설정")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "계정")
                }
            })

        TopAppBar {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Navi")
            }
            Text(text = "TopAppBar", modifier = Modifier.weight(1f))
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "검색")
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "설정")
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "계정")
            }
        }

        Text(text = "Hello $name!")
    }
}

@Composable
fun CheckBoxEx() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val (getter, setter) = remember { mutableStateOf(false) }
        Checkbox(checked = getter, onCheckedChange = setter)

        Text(text = "프로그래머입니다.", modifier = Modifier.clickable {
            setter(!getter)
        })
    }
}

@Composable
fun PhotoCard() {
}

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStudyTheme {
        CheckBoxEx()
    }
}