package com.example.mvi_compose_basic.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvi_compose_basic.activity.ui.theme.MviComposeBasicTheme
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState

@ExperimentalComposeUiApi
class ComposeMessageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviComposeBasicTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalculateScreen()
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun CalculateScreen() {
    var result by remember {
        mutableStateOf("")
    }
    var inputText by remember {
        mutableStateOf("")
    }
    var isCorrect by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "계산하기", modifier = Modifier.padding(bottom = 16.dp))
        Text(text = "10 + 5 = $result", modifier = Modifier.padding(bottom = 16.dp))
        TextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            label = {
                Text(text = "결과 입력")
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                result = inputText
                isCorrect = result == "15"
            })
        )

        val keyboardController = LocalSoftwareKeyboardController.current
        Button(onClick = {
            result = inputText
            keyboardController?.hide()
        }) {
            Text(text = "계산하기")
        }

        MessageBar(isCorrect)
    }
}

@Composable
fun MessageBar(isCorrect: Boolean) {
    val state = rememberMessageBarState()
    ContentWithMessageBar(messageBarState = state) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                if (isCorrect) {
                    state.addSuccess(message = "업데이트 성공!!")
                } else {
                    state.addError(exception = Exception("실패!!!"))
                }
            }) {
                Text(text = "여기를 클릭하시오.")
            }
        }
    }
}

@Composable
fun MessageBarCorrect() {
    val state = rememberMessageBarState()
    ContentWithMessageBar(messageBarState = state) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                state.addSuccess(message = "업데이트 성공!!")
            }) {
                Text(text = "여기를 클릭하시오.")
            }
        }
    }
}

@Composable
fun MessageBarError() {
    val state = rememberMessageBarState()
    ContentWithMessageBar(messageBarState = state) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                state.addError(exception = Exception("실패!!!"))
            }) {
                Text(text = "여기를 클릭하시오.")
            }
        }
    }
}

@Composable
fun Greeting5(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    MviComposeBasicTheme {
        Greeting5("Android")
    }
}