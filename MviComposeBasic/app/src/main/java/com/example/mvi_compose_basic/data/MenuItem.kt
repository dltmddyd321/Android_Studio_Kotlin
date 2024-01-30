package com.example.mvi_compose_basic.data

import com.example.mvi_compose_basic.R

data class MenuItem(
    val id: ScreenType,
    val textId: Int
)

enum class ScreenType {
    FIRST, SECOND, THIRD
}

val drawerScreens = listOf(
    MenuItem(ScreenType.FIRST, R.string.first_screen),
    MenuItem(ScreenType.SECOND, R.string.second_screen),
    MenuItem(ScreenType.THIRD, R.string.third_screen)
)