package com.example.mvi_compose_basic.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mvi_compose_basic.activity.ui.theme.MviComposeBasicTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@ExperimentalPagerApi
class PagerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TabPagerView()
                }
            }
        }
    }
}

val pages = listOf("First", "Second", "Third")

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabPagerView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()

        TabRow(selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch { pagerState.scrollToPage(index) }
                    })
            }
        }

//        VerticalPager(count = ) {
//
//        }

        HorizontalPager(count = pages.size, state = pagerState) { page ->
            Text(
                text = page.toString(),
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun PagerView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(count = 4, modifier = Modifier.fillMaxSize()) { page ->
            Text(
                text = page.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize(),
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun Greeting6(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    MviComposeBasicTheme {
        Greeting6("Android")
    }
}