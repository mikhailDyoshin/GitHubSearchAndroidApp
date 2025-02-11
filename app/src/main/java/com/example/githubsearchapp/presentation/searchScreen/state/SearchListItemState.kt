package com.example.githubsearchapp.presentation.searchScreen.state

sealed class SearchListItemState {
    data class UserState(
        val name: String,
        val avatarURL: String,
        val htmlURL: String?
    ) : SearchListItemState()

    data class RepositoryState(
        val name: String,
        val description: DescriptionState,
        val owner: String?,
        val statistics: List<StatisticState>,
    ) : SearchListItemState()

}