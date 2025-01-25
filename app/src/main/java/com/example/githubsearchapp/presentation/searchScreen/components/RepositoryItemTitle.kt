package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun RepositoryItemTitle(repositoryName: String, modifier: Modifier = Modifier) {
    Text(
        text = repositoryName,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = modifier
    )
}