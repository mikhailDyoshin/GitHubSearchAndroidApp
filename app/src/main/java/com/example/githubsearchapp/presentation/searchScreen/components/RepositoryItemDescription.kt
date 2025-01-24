package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubsearchapp.presentation.common.previewData.repositoryDescriptionState
import com.example.githubsearchapp.presentation.searchScreen.state.DescriptionState

@Composable
fun RepositoryItemDescription(state: DescriptionState) {

    Column {
        UserItem(
            userState = state.author
        )
        Text(text = "Создан: ${state.created}")
        Text(text = "Обновлен: ${state.updated}")
        Column {
            Text(text = "Описание:")
            Text(text = state.description)
        }
    }

}

@Preview
@Composable
fun RepositoryItemDescriptionPreview() {
    RepositoryItemDescription(state = repositoryDescriptionState)
}
