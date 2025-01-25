package com.example.githubsearchapp.presentation.searchScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg
import com.example.githubsearchapp.presentation.searchScreen.components.SearchField
import com.example.githubsearchapp.presentation.searchScreen.components.SearchScreenList
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchScreen(
    navigateToRepositoryContent: (RepositoryScreenNavArg) -> Unit,
    viewModel: SearchScreenViewModel = koinViewModel(),
    modifier: Modifier
) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val state = viewModel.listState.collectAsStateWithLifecycle(
        initialValue = SearchScreenListState(
            list = emptyList(),
            status = Resource.Status.SUCCESS,
            message = ""
        ),
        lifecycle = lifecycle
    ).value

    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(start = 10.dp, top = 5.dp, end = 10.dp)
    ) {
        SearchField(
            text = viewModel.searchInputState.value,
            status = state.status,
            updateSearchInput = { viewModel.updateSearchInput(it) },
            search = { viewModel.search() })
        SearchScreenList(
            state = state,
            onRetry = { viewModel.search() },
            navigateToRepositoryContent = { navigateToRepositoryContent(it) },
        )
    }
}
