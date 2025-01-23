package com.example.githubsearchapp.presentation.repositoryContentScreen.components



import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubsearchapp.R
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.models.RepositoryContentType
import com.example.githubsearchapp.presentation.repositoryContentScreen.state.RepositoryContentItemState
import com.example.githubsearchapp.presentation.repositoryContentScreen.state.RepositoryContentState
import com.example.githubsearchapp.ui.theme.DirIconColor
import com.example.githubsearchapp.ui.theme.FileIconColor
import androidx.compose.foundation.lazy.items

@Composable
fun RepositoryContentList(
    state: RepositoryContentState,
    onItemClick: (item: RepositoryContentItemState) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.repositoryContent) { item ->

            when (item.type) {
                RepositoryContentType.FILE -> RepositoryContentItem(
                    iconId = R.drawable.file_icon,
                    name = item.name ?: "",
                    iconColor = FileIconColor,
                    onClick = { onItemClick(item) })

                RepositoryContentType.DIR -> RepositoryContentItem(
                    iconId = R.drawable.folder_icon,
                    name = item.name ?: "",
                    iconColor = DirIconColor,
                    onClick = { onItemClick(item) })

                null -> {
                    // Do nothing
                }
            }
        }
    }
}

@Preview
@Composable
fun RepositoryContentListPreview() {

    val content = listOf(
        RepositoryContentItemState(
            name = ".idea",
            type = RepositoryContentType.DIR,
            path = "",
            htmlURL = ""
        ),
        RepositoryContentItemState(
            name = "abc",
            type = RepositoryContentType.DIR,
            path = "",
            htmlURL = ""
        ),
        RepositoryContentItemState(
            name = ".gitignore",
            type = RepositoryContentType.FILE,
            path = "",
            htmlURL = ""
        ),
        RepositoryContentItemState(
            name = "abc",
            type = RepositoryContentType.FILE,
            path = "",
            htmlURL = ""
        ),RepositoryContentItemState(
            name = "bcd",
            type = RepositoryContentType.FILE,
            path = "",
            htmlURL = ""
        )

    )

    RepositoryContentList(state = RepositoryContentState(repositoryContent =content, status = Resource.Status.SUCCESS)) {

    }
}