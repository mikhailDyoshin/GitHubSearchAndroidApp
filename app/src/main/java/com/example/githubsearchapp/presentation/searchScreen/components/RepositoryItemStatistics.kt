package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.githubsearchapp.presentation.common.ShadowWrapper
import com.example.githubsearchapp.presentation.searchScreen.state.StatisticState

@Composable
fun RepositoryItemStatistics(statistics: List<StatisticState>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.wrapContentWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        statistics.forEach {
            ShadowWrapper(
                wrapperModifier = Modifier.padding(bottom = 10.dp, start = 4.dp).wrapContentSize(),
                shadowModifier = Modifier.shadow(
                    4.dp,
                    shape = RoundedCornerShape(5.dp),
                    clip = false
                ),
                content = { StatisticsItem(it) }
            )
        }
    }
}