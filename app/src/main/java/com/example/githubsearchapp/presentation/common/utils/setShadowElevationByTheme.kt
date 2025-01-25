package com.example.githubsearchapp.presentation.common.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun setShadowElevationByTheme(elevation: Dp): Dp {
    return if (!isSystemInDarkTheme()) elevation else 0.dp
}