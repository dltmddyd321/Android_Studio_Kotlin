package com.example.mvi_compose_basic.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.compose.*
import com.example.mvi_compose_basic.R
import com.example.mvi_compose_basic.ui.theme.MviComposeBasicTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box {
                        AnimatedPreLoader(modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.Center))
                    }
                }
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            delay(5000) //초기 네트워크 및 DB 동작을 동기적으로 수행
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@Composable
fun AnimatedPreLoader(modifier: Modifier = Modifier) {
    //리소스 가져오기
    val preLoaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.testanim
        )
    )
    val isPlayLottie = remember { mutableStateOf(true) }

    //애니메이션 동작 설정
    val preLoaderProgress = animateLottieCompositionAsState(
        preLoaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlayLottie.value
    )

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        delay(5000)
        isPlayLottie.value = false
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        (context as? Activity)?.finish()
    }

    LottieAnimation(
        composition = preLoaderLottieComposition,
        progress = preLoaderProgress.value,
        modifier = modifier
    )
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MviComposeBasicTheme {
        Greeting2("Android")
    }
}