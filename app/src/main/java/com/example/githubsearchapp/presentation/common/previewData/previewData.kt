package com.example.githubsearchapp.presentation.common.previewData

import com.example.githubsearchapp.R
import com.example.githubsearchapp.presentation.searchScreen.state.DescriptionState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.presentation.searchScreen.state.StatisticState

val repositoryDescriptionState = DescriptionState(
    author = SearchListItemState.UserState(
        name = "John",
        avatarURL = "",
        htmlURL = ""
    ),
    created = "13.02.2012 17:29:58",
    updated = "07.03.2012 00:13:47",
    description = "Repository to study Rust programming language"
)

val repositoryItem = SearchListItemState.RepositoryState(
    name = "Rust-Tutorial and another part of a very long title",
    description = repositoryDescriptionState,
    owner = "John",
    statistics = listOf(
        StatisticState(
            iconId = R.drawable.star_icon,
            value = 4000
        ),
        StatisticState(
            iconId = R.drawable.eye_icon,
            value = 5000
        ),
        StatisticState(
            iconId = R.drawable.fork_icon,
            value = 3570
        )
    ),
)