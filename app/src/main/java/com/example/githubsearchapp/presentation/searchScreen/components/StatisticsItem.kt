package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.githubsearchapp.presentation.searchScreen.state.StatisticState

@Composable
fun StatisticsItem(state: StatisticState) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = state.value.toString())
        Icon(
            imageVector = ImageVector.vectorResource(id = state.iconId),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(20.dp)
                .padding(4.dp),
        )
    }
}