package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //View 안에 표시될 테마나 요소를 호출
            ComposeTutorialTheme{
                Conversation(message = SampleData.conversationSample)
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: com.example.myapplication.Message) {
    Row(modifier = Modifier.padding(all = 10.dp)) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Example Picture",
            //이미지의 모양과 크기 설정
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape))

        Spacer(modifier = Modifier.width(8.dp))

        //메시지 확장에 따른 효과 부여를 위한
        var isExpanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = msg.author,
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.subtitle2)

            Spacer(modifier = Modifier.height(4.dp))

            //Text 배경 도형 디자인 추가
            androidx.compose.material.Surface(shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = androidx.compose.ui.graphics.Color.Cyan,
                modifier = Modifier.animateContentSize().padding(1.dp)) {
                Text(text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    //메시지 클릭 시 전체 길이로 확장
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}


@Composable
fun Conversation(message: List<com.example.myapplication.Message>) {
    LazyColumn {
        items(message) { message ->
            MessageCard(msg = message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}
