package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.example.githubsearchapp.presentation.searchScreen.state.StatisticState

@Composable
fun RepositoryItemStatistics(statistics: List<StatisticState>) {
    Row {
        statistics.forEach {
            StatisticsItem(it)
        }
    }
}