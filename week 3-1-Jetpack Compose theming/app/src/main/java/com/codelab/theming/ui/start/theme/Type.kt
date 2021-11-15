package com.codelab.theming.ui.start.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.codelab.theming.R
import java.time.format.TextStyle

private val Montserrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_medium, FontWeight.W500),
    Font(R.font.montserrat_semibold, FontWeight.W600)
)

private val Domine = FontFamily(
    Font(R.font.domine_regular),
    Font(R.font.domine_bold, FontWeight.Bold)
)

val JetnewsTypography = Typography(
    h4 = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    h6 = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Domine,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Montserrat,
        fontSize = 14.sp
    ),
    button = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = androidx.compose.ui.text.TextStyle(
        fontFamily = com.codelab.theming.ui.finish.theme.Montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)

