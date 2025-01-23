package com.example.githubsearchapp.presentation.repositoryContentScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg

@Composable
fun RepositoryContentScreen(navArg: RepositoryScreenNavArg) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = navArg.toString())
    }
}