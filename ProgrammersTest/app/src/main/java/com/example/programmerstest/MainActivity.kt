package com.example.programmerstest

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs
import kotlin.math.max


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

fun main() {
    val scanner = Scanner(System.`in`)
    val p = scanner.nextInt()
    val res = mutableMapOf<Int, Int>()

    repeat(p) {
        val t = scanner.nextInt()
        val arr = mutableListOf<Int>()
        repeat(20) {
            arr.add(scanner.nextInt())
        }

        var cnt = 0
        for (i in 0 until 20) {
            val slice = arr.slice(0 until i)
            cnt += slice.count { it > arr[i] }
        }
        res[t] = cnt
    }
    res.forEach { (t, u) ->
        println("$t $u")
    }
}

fun mainChecker() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val castle = Array(N) { readLine().toCharArray() } //구조를 입력받는다.
    val row = BooleanArray(M) { false }
    val col = BooleanArray(N) { false }

    for (i in 0 until N) for (j in 0 until M) {
        if (castle[i][j] == 'X') {
            col[i] = true
            row[j] = true
        }
    }

    println(max(row.count { !it }, col.count { !it }))
}

fun mainAnt() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val W = st.nextToken().toInt()
    val H = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val q = st.nextToken().toInt() //q가 컬럼
    val p = st.nextToken().toInt() //p가 로우
    val t = br.readLine().toInt()
    var x = (q + t) % (2 * W)
    var y = (p + t) % (2 * H)
    x = (W - abs((W - x).toDouble())).toInt()
    y = (H - abs((H - y).toDouble())).toInt()
    println("$x $y")
}