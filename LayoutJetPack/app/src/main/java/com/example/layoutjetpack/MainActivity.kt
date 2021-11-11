package com.example.layoutjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.layoutjetpack.ui.theme.LayoutJetPackTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutJetPackTheme {
                //PhotographerCard()
                LayoutsCodeLab()
            }
        }
    }
}

//100개의 List를 나타내는 RecyclerView 형식 생성
@Composable
//fun ImageList() {

    //val listSize = 100

    ///val scrollState = rememberLazyListState()

    //val coroutineScope = rememberCoroutineScope()

    //Column {
        //Row {
            //Button(onClick = {
                //coroutineScope.run {
                    //scrollState.animateScrollToItem(0)
                //}
            //}) {
//Text("Scroll to the top")
//}
//Button(onClick = {
//coroutineScope.launch {
// listSize - 1 is the last index of the list
//scrollState.animateScrollToItem(listSize - 1)
//}
//}) {
//Text("Scroll to the end")
//}
//}
// LazyColumn(state = scrollState) {
//  items(listSize) {
//      ImageListItem(index = it)
//     }
//  }
//  }
//}

//List에 들어가는 Item의 속성을 지정하는 함수
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = rememberImagePainter(
            data = "https://developer.android.com/images/brand/Android_Robot.png"),
            contentDescription = "Android Logo",
        modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun LayoutsCodeLab() {
    //전체 Text 공간 확인
    Scaffold(
        topBar = {
            //App Top Bar Text 지정
            TopAppBar(
                title=  {
                    Text(text = "LayoutsCodeLab")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        //하트 아이콘 생성
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        //padding 간격 이어서 추가 가능
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(8.dp))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {

    //클릭 가능 및 클릭 시 반응 디자인에 대한 설정
    Row(
        modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = {})
            .padding(16.dp)) {
        //프로필 이미지 모양 설정
       Surface(
           modifier = Modifier.size(50.dp),
           shape = CircleShape,
           color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
       ) {

       }
        //Text Column 요소 설정
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

//버튼 생성 함수
//fun Button(
//    modifier: Modifier = Modifier,
//    onClick: (() -> Unit)? = null,
//    ...
//    content: @Composable () -> Unit
//)

//TopBar 설정
//TopAppBar(
//    title = {
//        Text(text = "Page title", maxLines = 2)
//    },
//    navigationIcon = {
//        Icon(myNavIcon)
//    }
//)

@Preview
@Composable
fun DefaultPreview() {
    LayoutJetPackTheme {
        //PhotographerCard()
        LayoutsCodeLab()
    }
}