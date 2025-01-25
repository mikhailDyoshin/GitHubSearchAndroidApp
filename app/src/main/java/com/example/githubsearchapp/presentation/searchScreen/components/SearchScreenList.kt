package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubsearchapp.common.Constants.START_SCREEN_STATUS_MESSAGE
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.presentation.common.ErrorScreen
import com.example.githubsearchapp.presentation.common.NoContentScreen
import com.example.githubsearchapp.presentation.common.previewData.repositoryItem
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState


@Composable
fun SearchScreenList(
    state: SearchScreenListState,
    onRetry: () -> Unit,
    navigateToRepositoryContent: (RepositoryScreenNavArg) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state.status) {
            Resource.Status.SUCCESS -> {
                SuccessSearchScreenList(
                    state = state,
                    navigateToRepositoryContent = { navigateToRepositoryContent(it) }
                )
            }

            Resource.Status.ERROR -> {
                ErrorScreen(onRetry = { onRetry() })
            }

            Resource.Status.LOADING -> {
                SearchLoadingScreen()
            }
        }
    }

}

@Composable
fun SuccessSearchScreenList(
    state: SearchScreenListState,
    navigateToRepositoryContent: (RepositoryScreenNavArg) -> Unit,
) {
    if (state.list.isEmpty() && state.message != START_SCREEN_STATUS_MESSAGE) {
        NoContentScreen()
    } else if (state.message == START_SCREEN_STATUS_MESSAGE) {
        StartScreen()
    } else {
        LazyColumn {
            items(state.list) { itemState ->
                when (itemState) {
                    is SearchListItemState.RepositoryState -> RepositoryItem(
                        state = itemState,
                        navigateToRepositoryContent = { navigateToRepositoryContent(it) }
                    )

                    is SearchListItemState.UserState -> UserItem(itemState)
                    null -> {
                        // Do nothing
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenListLoadingPreview() {
    SearchScreenList(
        state = SearchScreenListState(
            list = emptyList(),
            status = Resource.Status.LOADING
        ),
        onRetry = {},
        navigateToRepositoryContent = {}
    )
}

@Preview
@Composable
fun SearchScreenListErrorPreview() {
    SearchScreenList(
        state = SearchScreenListState(
            list = emptyList(),
            status = Resource.Status.ERROR,
            message = "No Internet"
        ),
        onRetry = {},
        navigateToRepositoryContent = {}
    )
}

@Preview
@Composable
fun SearchScreenLisSuccessPreview() {
    Column(modifier = Modifier.background(color = Color.White)) {
        SearchScreenList(
            state = SearchScreenListState(
                list = listOf(
                    SearchListItemState.UserState(
                        name = "Jack",
                        avatarURL = "",
                        htmlURL = ""
                    ),
                    repositoryItem,
                    SearchListItemState.UserState(
                        name = "Duck",
                        avatarURL = "",
                        htmlURL = ""
                    ),
                    SearchListItemState.UserState(
                        name = "Bob",
                        avatarURL = "",
                        htmlURL = ""
                    ),
                ),
                status = Resource.Status.SUCCESS
            ),
            onRetry = {},
            navigateToRepositoryContent = {}
        )
    }

}