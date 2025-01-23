package com.example.githubsearchapp.presentation.repositoryContentScreen.state

import com.example.githubsearchapp.domain.models.RepositoryContentType

data class RepositoryContentItemState(
    val name: String?,
    val type: RepositoryContentType?,
    val path: String?,
    val htmlURL: String?
)
