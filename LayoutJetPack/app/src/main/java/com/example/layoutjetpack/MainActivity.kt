package com.example.layoutjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.layoutjetpack.ui.theme.LayoutJetPackTheme
import com.google.android.material.chip.Chip
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
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
//    Column(modifier = modifier) {
//        Text(text = "Hi there!")
//        Text(text = "Thanks for going through the Layouts codelab")
//    }
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

//@Composable
//fun StaggeredGrid(
//    modifier: Modifier = Modifier,
//    rows: Int = 3,
//    content: @Composable () -> Unit
//) {
//    Layout(
//        modifier = modifier,
//        content = content
//    ) { measurables, constraints ->
//        val rowWidths = IntArray(rows) { 0 }
//        val rowHeights = IntArray(rows) { 0 }
//
//        val placeable = measurables.mapIndexed { index, measurable ->
//
//            val placeable = measurable.measure(constraints)
//
//            val row = index % rows
//            rowWidths[row] += placeable.width
//            rowHeights[row] = Math.max(rowHeights[row], placeable.height)
//
//            placeable
//
//            // Grid's width is the widest row
//            val width = rowWidths.maxOrNull()
//                ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth)) ?: constraints.minWidth
//
//            // Grid's height is the sum of the tallest element of each row
//            // coerced to the height constraints
//            val height = rowHeights.sumOf { it }
//                .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))
//
//            // Y of each row, based on the height accumulation of previous rows
//            val rowY = IntArray(rows) { 0 }
//            for (i in 1 until rows) {
//                rowY[i] = rowY[i-1] + rowHeights[i-1]
//
//
//        }
//    }
//}

@Composable
fun Chip(modifier: Modifier = Modifier, text: String) {
    Card(modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
        ) {
        Row(modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .size(16.dp, 16.dp)
                .background(color = MaterialTheme.colors.secondary)) {
                Spacer(Modifier.width(4.dp))
                Text(text = text)
            }
        }
    }
}

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)

//여러개의 아이템 리스트를 생성하기 위한 함수
//@Composable
//fun BodyContent(modifier: Modifier = Modifier) {
//    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
//        StaggeredGrid {
//            for (topic in topics) {
//                Chip(modifier = Modifier.padding(8.dp), text = topic)
//            }
//        }
//    }
//}

@Preview
@Composable
fun ChipPreview() {
    LayoutJetPackTheme {
        //BodyContent()
        Chip(text = "Hi there")
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

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseLine = placeable[FirstBaseline]

        val placeableY = firstBaselineToTop.roundToPx() - firstBaseLine
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    LayoutJetPackTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    LayoutJetPackTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}

//커스텀 레이아웃 구성
@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}
