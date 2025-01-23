package com.example.githubsearchapp.presentation.repositoryContentScreen.state

import com.example.githubsearchapp.common.Resource

data class RepositoryContentState(
    val repositoryContent: List<RepositoryContentItemState>,
    val status: Resource.Status,
    val message: String = ""
)
