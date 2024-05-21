package com.example.tooltip_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import com.example.tooltip_test.ui.theme.TooltipTestTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TooltipTestTheme {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CustomTooltipBox(TooltipDirection.Top)
                }
            }
        }
    }
}

enum class TooltipDirection {
    Top, Bottom, Left, Right
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTooltipBox(
    direction: TooltipDirection
) {
    val tooltipState = remember { TooltipState() }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
        TooltipBox(
            state = tooltipState,
            positionProvider = remember {
                CustomTooltipPositionProvider(direction)
            },
            tooltip = {
                when (direction) {
                    TooltipDirection.Top -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.DarkGray)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "툴팁 텍스트",
                                    modifier = Modifier.padding(8.dp),
                                    color = Color.White,
                                )
                            }
                            Canvas(modifier = Modifier.size(10.dp)) {
                                drawTooltipArrow(this, Color.DarkGray, direction)
                            }
                        }
                    }
                    TooltipDirection.Bottom -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Canvas(modifier = Modifier.size(10.dp)) {
                                drawTooltipArrow(this, Color.DarkGray, direction)
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.DarkGray)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "툴팁 텍스트",
                                    modifier = Modifier.padding(8.dp),
                                    color = Color.White,
                                )
                            }
                        }
                    }
                    TooltipDirection.Left -> {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.DarkGray)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "툴팁 텍스트",
                                    modifier = Modifier.padding(8.dp),
                                    color = Color.White,
                                )
                            }
                            Canvas(modifier = Modifier.size(10.dp)) {
                                drawTooltipArrow(this, Color.DarkGray, direction)
                            }
                        }
                    }
                    TooltipDirection.Right -> {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Canvas(modifier = Modifier.size(10.dp)) {
                                drawTooltipArrow(this, Color.DarkGray, direction)
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.DarkGray)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "툴팁 텍스트",
                                    modifier = Modifier.padding(8.dp),
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
            },
            content = {
                Box(
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(16.dp)
                        .clickable {
                            coroutineScope.launch { tooltipState.show() }
                        }
                ) {
                    Text("Hover me")
                }
            }
        )
    }
}

fun DrawScope.drawTooltipArrow(scope: DrawScope, color: Color, direction: TooltipDirection) {
    val path = Path().apply {
        when (direction) {
            TooltipDirection.Top -> {
                moveTo(0f, 0f)
                lineTo(size.width / 2, size.height)
                lineTo(size.width, 0f)
            }
            TooltipDirection.Bottom -> {
                moveTo(0f, size.height)
                lineTo(size.width / 2, 0f)
                lineTo(size.width, size.height)
            }
            TooltipDirection.Left -> {
                moveTo(size.width, size.height / 2)
                lineTo(0f, 0f)
                lineTo(0f, size.height)
            }
            TooltipDirection.Right -> {
                moveTo(0f, size.height / 2)
                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
            }
        }
        close()
    }
    scope.drawPath(
        path = path,
        color = color
    )
}

class CustomTooltipPositionProvider(private val direction: TooltipDirection) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val x: Int
        val y: Int
        when (direction) {
            TooltipDirection.Top -> {
                x = anchorBounds.left + (anchorBounds.width / 2) - (popupContentSize.width / 2)
                y = anchorBounds.top - popupContentSize.height
            }
            TooltipDirection.Bottom -> {
                x = anchorBounds.left + (anchorBounds.width / 2) - (popupContentSize.width / 2)
                y = anchorBounds.bottom
            }
            TooltipDirection.Left -> {
                x = anchorBounds.left - popupContentSize.width
                y = anchorBounds.top + (anchorBounds.height / 2) - (popupContentSize.height / 2)
            }
            TooltipDirection.Right -> {
                x = anchorBounds.right
                y = anchorBounds.top + (anchorBounds.height / 2) - (popupContentSize.height / 2)
            }
        }
        return IntOffset(x, y)
    }
}

@Preview
@Composable
fun TestPreView() {
}