package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 2.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = state.value.toString(), maxLines = 1)
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