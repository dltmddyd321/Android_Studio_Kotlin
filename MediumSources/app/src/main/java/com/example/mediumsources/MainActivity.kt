package com.example.mediumsources

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediumsources.ui.theme.MediumSourcesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediumSourcesTheme {
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
fun ImageItem(imageResId: Int) {
    val image = painterResource(id = imageResId)

    Image(
        painter = image,
        contentDescription = "Test Description",
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    )
}

@Composable
fun LoginScreen() {
    var userEmail by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    Column {
        ImageItem(imageResId = R.drawable.ic_launcher_foreground)

        OutlinedTextField(value = userEmail, onValueChange = {
            userEmail = it //입력된 값을 변수에 저장
        }, label = { Text(text = "이메일 입력") })

        OutlinedTextField(
            value = userEmail,
            onValueChange = {
                userEmail = it //입력된 값을 변수에 저장
            },
            label = { Text(text = "비밀번호 입력") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        TextButton(onClick = { /*TODO*/ }, modifier = Modifier
            .padding(40.dp), content = {
            Text(text = "비밀번호 찾기")
        })

        OutlinedButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(50.dp, 50.dp)
        ) {
            Text(text = "로그인")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MediumSourcesTheme {
        LoginScreen()
    }
}