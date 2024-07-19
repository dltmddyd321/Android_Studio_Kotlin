package com.example.composebasic_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic_1.ui.theme.ComposeBasic_1Theme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasic_1Theme {
                // A surface container using the 'background' color from the theme
                //primary 값에 해당하는 background 색상이 설정
//                Surface(color = MaterialTheme.colors.primary) {
//                    Greeting("Buddy!")
//                }
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }

    if(shouldShowOnboarding) {
        OnBoardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    } else {
        Greetings()
    }
}

//@Composable
//private fun MyApp(names : List<String> = listOf("World", "Compose")) {
//    //List 형식으로 Greeting 함수의 인자값으로 들어갈 Text 값을 배치 가능
//    Column(modifier = Modifier.padding(vertical = 4.dp)) {
//        for (name in names) {
//            Greeting(name = name)
//        }
//    }
////    Surface(color = MaterialTheme.colors.background) {
////        Greeting("Android")
////    }
//}

@Composable
fun Greeting(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

//Text 객체 팝업 함수 Greeting
@Composable
fun CardContent(name: String) {
    
    //개체 View 확장을 위한 변수
    var expanded by remember {
        mutableStateOf(false)
    }

    Row(modifier = Modifier
        .padding(12.dp)
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(text = name,
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.ExtraBold
            ))
            if(expanded) {
                Text(text = ("Composem ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy. ").repeat(4),)
            }
        }
    }
    IconButton(onClick = { expanded = !expanded }) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = if (expanded) {
                stringResource(id = R.string.show_less)
            } else {
                stringResource(id = R.string.show_more)
            }
        )
    }
//    //개체 확장 시 dp 값 변수
//    val extraPadding by animateDpAsState(
//        if (expanded) 48.dp else 0.dp,
//        //dp에 따른 애니메이션 효과 적용
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//    )
//
//    //background 색상 값을 설정하고 padding 값 조절
//    Surface(color = MaterialTheme.colors.primary,
//            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        //버튼과 텍스트는 행으로 정렬하기 위해 상위에 Row 처리
//        Row(modifier = Modifier.padding(24.dp)) {
//            Column(modifier = Modifier
//                .weight(1f)
//                .padding(bottom = extraPadding.coerceAtLeast(0.dp))
//            ) {
//                Text(text = "Hello, ")
//                Text(text = name)
//            }
//            //Button 생성 및 Button Text 설정
//            OutlinedButton(onClick = { expanded= !expanded }
//            ) {
//                Text(if (expanded) "Show less" else "Show more")
//            }
//        }
//        //Text 요소를 열로 배치
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .padding(24.dp)) {
//            Text(text = "Hello, ")
//            Text(text = name)
//        }
//        OutlinedButton(onClick = { /*TODO*/ }) {
//            Text("Show more")
//        }
//        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
//    }
}

@Composable
fun OnBoardingScreen(onContinueClicked : () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics CodeLab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

//name 인자 값에 들어갈 Text List 선언 함수
//$it => List's Index Number
@Composable
fun Greetings(names: List<String> = List(1000) { "$it" }) {
    //RecyclerView 같은 상/하 슬라이드로 List 만큼의 객체 구성
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    ComposeBasic_1Theme {
        OnBoardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeBasic_1Theme {
        Greetings()
    }
//    ComposeBasic_1Theme {
//        MyApp()
//    }
}

@Composable
@UiComposable
fun TimeBlocksTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    minItemWidth: Dp = 90.dp,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    edgePadding: Dp = ScrollableTabRowPadding,
    indicator: @Composable (tabPositions: List<TabPosition>) -> Unit,
    tabs: @Composable @UiComposable () -> Unit
) {
    Surface(
        modifier = modifier,
        color = backgroundColor,
        contentColor = contentColor
    ) {
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        val scrollableTabData = remember(scrollState, coroutineScope) {
            ScrollableTabData(
                scrollState = scrollState,
                coroutineScope = coroutineScope
            )
        }
        SubcomposeLayout(
            Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.CenterStart)
                .horizontalScroll(scrollState)
                .selectableGroup()
                .clipToBounds()
        ) { constraints ->
            val minTabWidth = minItemWidth.roundToPx()
            val padding = edgePadding.roundToPx()
            val tabConstraints = constraints.copy(minWidth = minTabWidth)
            val tabPlaceables = subcompose(TabSlots.Tabs, tabs)
                .map { it.measure(tabConstraints) }
            var layoutWidth = padding * 2
            var layoutHeight = 0
            tabPlaceables.forEach {
                layoutWidth += it.width
                layoutHeight = maxOf(layoutHeight, it.height)
            }

            layout(layoutWidth, layoutHeight) {
                val tabPositions = mutableListOf<TabPosition>()
                var left = padding
                tabPlaceables.forEach {
                    it.placeRelative(left, 0)
                    tabPositions.add(TabPosition(left = left.toDp(), width = it.width.toDp()))
                    left += it.width
                }

                subcompose(TabSlots.Indicator) {
                    indicator(tabPositions)
                }.forEach {
                    it.measure(Constraints.fixed(layoutWidth, layoutHeight)).placeRelative(0, 0)
                }

                scrollableTabData.onLaidOut(
                    density = this@SubcomposeLayout,
                    edgeOffset = padding,
                    tabPositions = tabPositions,
                    selectedTab = selectedTabIndex
                )
            }
        }
    }
    Divider(modifier = Modifier
        .zIndex(1f)
        .padding(bottom = (0.5).dp))
}

@Immutable
class TabPosition internal constructor(val left: Dp, val width: Dp) {
    val right: Dp get() = left + width

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TabPosition) return false

        if (left != other.left) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + width.hashCode()
        return result
    }

    override fun toString(): String {
        return "TabPosition(left=$left, right=$right, width=$width)"
    }
}

object TabRowDefaults {
    /**
     * @param modifier modifier for the divider's layout
     * @param thickness thickness of the divider
     * @param color color of the divider
     */
    @Composable
    fun Divider(
        modifier: Modifier = Modifier,
        thickness: Dp = DividerThickness,
        color: Color = LocalContentColor.current.copy(alpha = DividerOpacity)
    ) {
        androidx.compose.material.Divider(modifier = modifier, thickness = thickness, color = color)
    }

    /**
     * @param modifier modifier for the indicator's layout
     * @param height height of the indicator
     * @param color color of the indicator
     */
    @Composable
    fun Indicator(
        modifier: Modifier = Modifier,
        height: Dp = IndicatorHeight,
        color: Color = LocalContentColor.current
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .height(height)
                .background(color = color)
        )
    }

    private val DividerOpacity = 0.12f
    private val DividerThickness = 1.dp
    private val IndicatorHeight = 2.dp
}

fun Modifier.tabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

private enum class TabSlots {
    Tabs,
    Divider,
    Indicator
}

private class ScrollableTabData(
    private val scrollState: ScrollState,
    private val coroutineScope: CoroutineScope
) {
    private var selectedTab: Int? = null

    fun onLaidOut(
        density: Density,
        edgeOffset: Int,
        tabPositions: List<TabPosition>,
        selectedTab: Int
    ) {
        if (this.selectedTab != selectedTab) {
            this.selectedTab = selectedTab
            tabPositions.getOrNull(selectedTab)?.let {
                val calculatedOffset = it.calculateTabOffset(density, edgeOffset, tabPositions)
                if (scrollState.value != calculatedOffset) {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(
                            calculatedOffset,
                            animationSpec = tween(
                                durationMillis = 250,
                                easing = FastOutSlowInEasing
                            )
                        )
                    }
                }
            }
        }
    }

    private fun TabPosition.calculateTabOffset(
        density: Density,
        edgeOffset: Int,
        tabPositions: List<TabPosition>
    ): Int = with(density) {
        val totalTabRowWidth = tabPositions.last().right.roundToPx() + edgeOffset
        val visibleWidth = totalTabRowWidth - scrollState.maxValue
        val tabOffset = left.roundToPx()
        val scrollerCenter = visibleWidth / 2
        val tabWidth = width.roundToPx()
        val centeredTabOffset = tabOffset - (scrollerCenter - tabWidth / 2)
        val availableSpace = (totalTabRowWidth - visibleWidth).coerceAtLeast(0)
        return centeredTabOffset.coerceIn(0, availableSpace)
    }
}
