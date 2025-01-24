package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.githubsearchapp.presentation.searchScreen.state.DescriptionState

@Composable
fun RepositoryItemDescription(state: DescriptionState) {

    Column {
        UserItem(
            userState = state.author
        )
        Text(text = state.created)
        Text(text = state.updated)
        Text(text = state.description)
    }

}