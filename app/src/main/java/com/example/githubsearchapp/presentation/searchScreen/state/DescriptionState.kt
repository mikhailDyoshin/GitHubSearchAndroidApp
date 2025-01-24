package com.example.githubsearchapp.presentation.searchScreen.state

data class DescriptionState(
    val author: SearchListItemState.UserState,
    val created: String,
    val updated: String,
    val description: String
)
