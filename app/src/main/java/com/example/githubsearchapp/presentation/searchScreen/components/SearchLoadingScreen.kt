package com.example.githubsearchapp.presentation.searchScreen.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.githubsearchapp.presentation.common.ShimmerEffect
import com.example.githubsearchapp.presentation.common.utils.setColorByTheme
import com.example.githubsearchapp.ui.theme.GitHubSearchAppTheme

@Composable
fun SearchLoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        ShimmerEffect(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .fillMaxSize(),
            durationMillis = 1000
        ) {
            Column(
                Modifier
                    .background(color = Color.Transparent)
                    .zIndex(1f)
                    .align(Alignment.TopCenter)
            ) {
                repeat(6) {
                    Column(
                        modifier = Modifier
                            .zIndex(1f)
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .background(
                                setColorByTheme(
                                    lightThemeColor = Color.LightGray,
                                    darkThemeColor = MaterialTheme.colorScheme.tertiary
                                ), RoundedCornerShape(5.dp)
                            )
                    ) { }
                }
            }
        }

    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun SearchLoadingScreenPreview() {
    GitHubSearchAppTheme {
        SearchLoadingScreen()
    }
}
