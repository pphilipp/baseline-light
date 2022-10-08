package com.poid.baseline.light.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Purple900 = Color(0xFF240075)

val Teal100 = Color(0xFF66ADA6)
val Teal200 = Color(0xFF358880)

val Blue500 = Color(0xFF0B1ECC)


val PriorityHigh = Color(0xFFCE3333)
val PriorityMedium = Color(0xFFFFC107)
val PriorityLow = Color(0xFF8BC34A)
val PriorityNone = Color(0xFF696B6B)

val LightGray = Color(0xFFA2A5A5)
val MediumGray = Color(0xFF696B6B)
val DarkGray = Color(0xFF191A1A)

val Colors.DetailsItemBackgroundColor: Color
    @Composable
    get() = if(isLight) {
        LightGray
    } else {
        Color.White
    }
val Colors.DetailsItemTextColor: Color
    @Composable
    get() = if(isLight) {
        Color.White
    } else {
        DarkGray
    }

val Colors.TopAppBarContentColor: Color
    @Composable
    get() = if(isLight) {
        Color.White
    } else {
        LightGray
    }

val Colors.TopAppBarBackgroundColor: Color
    @Composable
    get() = if(isLight) {
        MediumGray
    } else {
        Blue500
    }