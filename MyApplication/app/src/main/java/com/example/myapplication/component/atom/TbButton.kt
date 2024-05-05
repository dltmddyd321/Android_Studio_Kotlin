package com.example.myapplication.component.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

data class Appearance(
    val backgroundColor: Color,
    val textColor: Color,
    val textSize: TextUnit
)

@Composable
fun TbButton(
    onClick: () -> Unit,
    appearance: Appearance,
    size: Dp,
    iconId: Int,
    label: String,
    loading: Boolean,
    iconOnly: Boolean
) {

    val isLoading = remember { mutableStateOf(loading) }
    rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        delay(3000)
        isLoading.value = false
    }

    Box(
        modifier = Modifier
            .size(size)
            .padding(16.dp)
            .background(color = appearance.backgroundColor, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            } else {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = "Icon",
                    tint = Color(0xFF007BFF),
                    modifier = Modifier.size(24.dp)
                )
            }
            if (!iconOnly) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = label,
                    color = appearance.textColor,
                    fontSize = appearance.textSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}