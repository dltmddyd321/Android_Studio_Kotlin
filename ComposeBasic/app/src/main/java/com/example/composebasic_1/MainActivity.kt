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
