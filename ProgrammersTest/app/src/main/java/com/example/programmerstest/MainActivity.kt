package com.example.programmerstest

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import com.example.programmerstest.ui.theme.ProgrammersTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = Color.BLUE
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars =
            false

        setContent {
            ProgrammersTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {
    var userEmail by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageItem(imgResId = R.drawable.ic_launcher_background)

        //이메일 입력 필드
        OutlinedTextField(value = userEmail, onValueChange = { userEmail = it },
            label = { Text(text = stringResource(id = R.string.app_name)) })

        //비밀번호 입력 필드
        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text(text = stringResource(id = R.string.app_name)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(end = 40.dp)
                .align(Alignment.End)
        ) {
            Text(text = "비밀번호 찾기", textAlign = TextAlign.End)
        }
    }
}

@Composable
fun ImageItem(imgResId: Int) {
    val img = painterResource(id = imgResId)

    Image(
        painter = img, contentDescription = null, modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Greeting(name: String) {
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProgrammersTestTheme {
        Greeting("Android")
    }
}