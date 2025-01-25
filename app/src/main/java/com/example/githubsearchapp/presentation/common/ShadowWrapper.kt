package com.example.githubsearchapp.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ShadowWrapper(
    wrapperModifier: Modifier,
    shadowModifier: Modifier,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    Column(wrapperModifier) {
        Column(
            modifier = shadowModifier
        ) {
            content()
        }
    }
}