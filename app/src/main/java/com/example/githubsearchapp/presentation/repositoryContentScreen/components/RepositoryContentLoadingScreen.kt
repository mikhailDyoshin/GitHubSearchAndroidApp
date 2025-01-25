package com.example.githubsearchapp.presentation.repositoryContentScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.githubsearchapp.presentation.common.ShimmerEffect

@Composable
fun RepositoryContentLoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        ShimmerEffect(
            modifier = Modifier
                .fillMaxSize(),
            durationMillis = 1000
        ) {
            Column(
                Modifier
                    .zIndex(1f)
                    .align(Alignment.TopCenter)
            ) {
                repeat(6) {
                    LoadingItem()
                }
            }
        }

    }
}

@Composable
fun LoadingItem() {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                Modifier
                    .size(30.dp)
                    .background(color = Color.LightGray, shape = CircleShape)
            ) { }
            Column(
                Modifier
                    .padding(start = 8.dp)
                    .width(250.dp)
                    .height(30.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) { }
        }

        HorizontalDivider(thickness = 2.dp, color = Color.Gray)
    }
}

@Preview
@Composable
fun RepositoryContentLoadingScreenPreview() {
    RepositoryContentLoadingScreen()
}