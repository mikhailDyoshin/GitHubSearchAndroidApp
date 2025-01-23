package com.example.githubsearchapp.presentation.searchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

@Composable
fun SearchScreen(
    navigateToRepositoryContent: (RepositoryScreenNavArg) -> Unit,
    viewModel: SearchScreenViewModel = koinViewModel(),
    modifier: Modifier
) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val state = viewModel.state.collectAsStateWithLifecycle(
        initialValue = SearchScreenListState(
            list = emptyList(),
            status = Resource.Status.SUCCESS,
            message = ""
        ),
        lifecycle = lifecycle
    ).value

    Column(
        modifier = modifier
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

//    readAndLogResourceStatus(
//        state = state,
//        onSuccess = { data ->
//            Log.d(
//                "ScreenResult",
//                "Success: data-size -> ${data?.size}"
//            )
//        },
//        onError = { error ->
//            Log.d(
//                "ScreenResult",
//                "Error: ${error?.message}"
//            )
//        },
//        onLoading = {
//            Log.d(
//                "ScreenResult",
//                "Loading..."
//            )
//        },
//    )
}

@Composable
fun LoadingStateHolder() {
    Text(text = "Loading...")
}

@Composable
fun SuccessStateHolder(data: List<Any>?) {
    Text(text = "Success: data-size -> ${data?.size}")
}

@Composable
fun ErrorStateHolder(error: Resource.Error?) {
    Text(text = "Error: ${error?.message}")
}

@Composable
fun ResourceStateHolder(
    state: Resource<List<Any>>,
    onLoading: @Composable () -> Unit,
    onSuccess: @Composable (data: List<Any>?) -> Unit,
    onError: @Composable (error: Resource.Error?) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state.status) {
            Resource.Status.SUCCESS -> onSuccess(state.data)
            Resource.Status.ERROR -> onError(state.error)
            Resource.Status.LOADING -> onLoading()
        }
    }
}

private fun readAndLogResourceStatus(
    state: Resource<List<Any>>,
    onSuccess: (data: List<Any>?) -> Unit,
    onError: (error: Resource.Error?) -> Unit,
    onLoading: () -> Unit
) {
    when (state.status) {
        Resource.Status.SUCCESS -> {
            onSuccess(state.data)
        }

        Resource.Status.ERROR -> {
            onError(state.error)
        }

        Resource.Status.LOADING -> {
            onLoading()
        }
    }
}