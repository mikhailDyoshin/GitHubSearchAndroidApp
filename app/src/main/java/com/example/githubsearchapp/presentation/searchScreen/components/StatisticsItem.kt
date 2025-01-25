package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.example.githubsearchapp.presentation.common.utils.setColorByTheme
import com.example.githubsearchapp.presentation.searchScreen.state.StatisticState
import com.example.githubsearchapp.ui.theme.DarkThemeDetailsBackgroundColor
import com.example.githubsearchapp.ui.theme.LightThemeDetailsBackgroundColor

@Composable
fun StatisticsItem(state: StatisticState) {

    val backgroundColor = setColorByTheme(
        lightThemeColor = LightThemeDetailsBackgroundColor,
        darkThemeColor = DarkThemeDetailsBackgroundColor
    )

    Row(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .background(color = backgroundColor, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 2.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = state.value.toString(), maxLines = 1, modifier = Modifier.wrapContentWidth())
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