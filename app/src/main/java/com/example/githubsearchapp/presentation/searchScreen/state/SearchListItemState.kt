package com.example.githubsearchapp.presentation.searchScreen.state

sealed class SearchListItemState {
    data class UserState(
        val name: String,
        val avatarURL: String,
        val htmlURL: String?
    ) : SearchListItemState()

    data class RepositoryState(
        val name: String,
        val description: String,
        val numberOfForks: Int,
        val owner: String?,
        val watchers: Int,
        val forks: Int,
        val stars: Int,
        val created: String,
        val updated: String
    ) : SearchListItemState()

}