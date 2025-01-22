package com.example.githubsearchapp.presentation.searchScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.githubsearchapp.common.Resource
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(viewModel: SearchScreenViewModel = koinViewModel(), modifier: Modifier) {

    val state = viewModel.state.collectAsState().value

    ResourceStateHolder(
        state = state,
        onLoading = { LoadingStateHolder() },
        onSuccess = { SuccessStateHolder(it) },
        onError = { ErrorStateHolder(it) },
        modifier = modifier
    )

    readAndLogResourceStatus(
        state = state,
        onSuccess = { data ->
            Log.d(
                "ScreenResult",
                "Success: data-size -> ${data?.size}"
            )
        },
        onError = { error ->
            Log.d(
                "ScreenResult",
                "Error: ${error?.message}"
            )
        },
        onLoading = {
            Log.d(
                "ScreenResult",
                "Loading..."
            )
        },
    )
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