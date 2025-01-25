package com.example.githubsearchapp.presentation.common.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun setColorByTheme(lightThemeColor: Color, darkThemeColor: Color): Color {
    return if (isSystemInDarkTheme()) darkThemeColor else lightThemeColor
}