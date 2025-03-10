package com.example.mvi_compose_basic.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.mvi_compose_basic.ExampleViewModel
import com.example.mvi_compose_basic.R
import com.example.mvi_compose_basic.SwipeButton
import com.example.mvi_compose_basic.component.DrawerBody
import com.example.mvi_compose_basic.component.NavHost
import com.example.mvi_compose_basic.component.TopBar
import com.example.mvi_compose_basic.data.ExampleEvent
import com.example.mvi_compose_basic.data.ExampleState
import com.example.mvi_compose_basic.data.drawerScreens
import com.example.mvi_compose_basic.ui.theme.MviComposeBasicTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: ExampleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviComposeBasicTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    startActivity(Intent(this, CalendarActivity::class.java))
//                    DrawerNavigationScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DrawerNavigationScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            TopBar(titleResId = R.string.app_name,
                openDrawer = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
        },
        drawerGesturesEnabled = true,
        drawerContent = {
            DrawerBody(menuItems = drawerScreens, scaffoldState = scaffoldState, scope = scope) {
                navController.navigate(it.id.name) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        }
    ) {
        NavHost(navController = navController)
    }
}

@Composable
fun SwitchWithIconExample() {
    var checked by remember {
        mutableStateOf(false)
    }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colors.primary,
            checkedTrackColor = MaterialTheme.colors.primaryVariant,
            uncheckedThumbColor = MaterialTheme.colors.secondary,
            uncheckedTrackColor = MaterialTheme.colors.secondaryVariant,
        ),
        modifier = Modifier.scale(1.5f), //Switch의 사이즈는 scale로 조정해야한다.
    )
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun SnackBarExample(string: String) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {

            ExtendedFloatingActionButton(
                onClick = {
                    // 스낵바 실행 부분
                    scope.launch {
                        val result = snackBarHostState.showSnackbar( // 스낵바 결과 받기 가능
                            message = string,
                            actionLabel = "close",
                            duration = SnackbarDuration.Short
                        )

                        when (result) {
                            SnackbarResult.Dismissed -> {
                                // 스낵바 닫기
                            }
                            SnackbarResult.ActionPerformed -> {
                                // 동작 수행
                            }
                        }
                    }
                }, text = { Text("Show snackbar") }
            )
        },
        content = { innerPadding ->
            Text(
                text = "SnackBar 예제",
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }
    )
}

@Composable
fun EditPlusButtonScreen(
    state: ExampleState,
    sendEvent: (ExampleEvent) -> Unit
) {
    var valueText by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    LaunchedEffect(state.result, showSnackBar) {
        if (state.result != null) {
            Toast.makeText(context, state.result, Toast.LENGTH_SHORT).show()
            sendEvent(ExampleEvent.MessageShown)
        }
        if (showSnackBar) {
            val result = snackBarHostState.showSnackbar( // 스낵바 결과 받기 가능
                message = valueText,
                actionLabel = "close",
                duration = SnackbarDuration.Short
            )

            when (result) {
                SnackbarResult.Dismissed -> {
                    // 스낵바 닫기
                }
                SnackbarResult.ActionPerformed -> {
                    // 동작 수행
                }
            }
            showSnackBar = false
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.isSending) {
            CircularProgressIndicator()
        } else {
            OutlinedTextField(value = state.text, onValueChange = {
                sendEvent(ExampleEvent.TextChanged(it))
                valueText = it
            }, label = {
                Text(text = "MESSAGE!")
            })
            Button(onClick = { sendEvent(ExampleEvent.SendClicked) }) {
                showSnackBar = true
                Text(text = "SEND!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MviComposeBasicTheme {
        Greeting("Android")
    }
}