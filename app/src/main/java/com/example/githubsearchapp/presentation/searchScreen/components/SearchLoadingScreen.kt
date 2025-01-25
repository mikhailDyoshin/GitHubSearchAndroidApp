package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.githubsearchapp.presentation.common.ShimmerEffect

@Composable
fun SearchLoadingScreen() {
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
            Column(Modifier.zIndex(1f).align(Alignment.TopCenter)) {
                repeat(6) {
                    Column(
                        modifier = Modifier
                            .zIndex(1f)
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .background(Color.LightGray, RoundedCornerShape(5.dp))
                    ) { }
                }
            }
        }

    }
}