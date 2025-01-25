package com.example.githubsearchapp.presentation.repositoryContentScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.models.RepositoryContentType
import com.example.githubsearchapp.presentation.common.ErrorScreen
import com.example.githubsearchapp.presentation.common.LoadingIndicator
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg
import com.example.githubsearchapp.presentation.repositoryContentScreen.components.RepositoryContentList
import com.example.githubsearchapp.presentation.repositoryContentScreen.components.RepositoryContentLoadingScreen
import com.example.githubsearchapp.presentation.repositoryContentScreen.state.RepositoryContentItemState
import com.example.githubsearchapp.presentation.repositoryContentScreen.state.RepositoryContentState
import com.example.githubsearchapp.ui.theme.RepositoryPathBarBackgroundColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun RepositoryContentScreen(
    navArg: RepositoryScreenNavArg,
    viewModel: RepositoryContentScreenViewModel = koinViewModel(),
    openDirectory: (navArg: RepositoryScreenNavArg) -> Unit,
    openFile: (htmlURL: String?) -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val state = viewModel.state.collectAsStateWithLifecycle(
        initialValue = RepositoryContentState(
            repositoryContent = emptyList(),
            status = Resource.Status.SUCCESS
        ),
        lifecycle = lifecycle
    ).value

    LaunchedEffect(Unit) {
        viewModel.getContent(navArg)
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = RepositoryPathBarBackgroundColor)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${navArg.owner}/${navArg.repository}/${navArg.path}",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (state.status) {
                Resource.Status.SUCCESS -> RepositoryContentList(
                    state = state,
                    onItemClick = { item ->
                        resolveOnItemClick(
                            item = item,
                            onDirectoryClick = { openDirectory(it) },
                            onFileCLick = { openFile(it) },
                            currentNavArg = navArg
                        )
                    },
                )

                Resource.Status.ERROR -> ErrorScreen(
                    errorMessage = state.message,
                    onRetry = {
                        viewModel.getContent(navArg)
                    }
                )

                Resource.Status.LOADING -> RepositoryContentLoadingScreen()
            }

        }
    }
}

fun resolveOnItemClick(
    currentNavArg: RepositoryScreenNavArg,
    item: RepositoryContentItemState,
    onDirectoryClick: (navArg: RepositoryScreenNavArg) -> Unit,
    onFileCLick: (htmlURL: String?) -> Unit
) {
    when (item.type) {
        RepositoryContentType.FILE -> {
            onFileCLick(item.htmlURL)
        }

        RepositoryContentType.DIR -> {
            onDirectoryClick(
                RepositoryScreenNavArg(
                    owner = currentNavArg.owner,
                    repository = currentNavArg.repository,
                    path = item.path ?: ""
                )
            )
        }

        null -> {
            Log.e("OnRepositoryContentItemClick", "Item's type is NULL")
        }
    }
}
