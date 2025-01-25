package com.example.githubsearchapp.presentation.searchScreen.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubsearchapp.presentation.common.previewData.repositoryItem
import com.example.githubsearchapp.presentation.common.utils.setShadowElevationByTheme
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.ui.theme.GitHubSearchAppTheme

@Composable
fun RepositoryItem(
    state: SearchListItemState.RepositoryState,
    navigateToRepositoryContent: (RepositoryScreenNavArg) -> Unit,
) {

    val showDescription: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }

    val cornerSize = 5.dp

    Column(Modifier.padding(bottom = 10.dp)) {
        Column(
            modifier = Modifier
                .shadow(setShadowElevationByTheme(4.dp), shape = RoundedCornerShape(cornerSize), clip = true)
                .clickable {
                    if (state.owner != null) {
                        navigateToRepositoryContent(
                            RepositoryScreenNavArg(
                                owner = state.owner,
                                repository = state.name,
                            )
                        )
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(cornerSize)
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RepositoryItemTitle(state.name, modifier = Modifier.weight(0.7f, true))
                    RepositoryItemStatistics(
                        state.statistics,
                        modifier = Modifier.weight(1f, false)
                    )
                }
                OpenDescriptionButton(
                    showDescription = showDescription.value,
                    openDescription = { showDescription.value = !showDescription.value })
                AnimatedVisibility(
                    visible = showDescription.value,
                    enter = expandVertically(
                        animationSpec = tween(durationMillis = 500),
                        expandFrom = Alignment.Bottom
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(durationMillis = 500),
                        shrinkTowards = Alignment.Bottom
                    )
                ) {
                    RepositoryItemDescription(state = state.description)
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
fun RepositoryItemPreview() {
    GitHubSearchAppTheme {
        Column(modifier = Modifier.background(color = Color.White)) {
            RepositoryItem(
                state = repositoryItem,
                navigateToRepositoryContent = {}
            )
        }
    }
}