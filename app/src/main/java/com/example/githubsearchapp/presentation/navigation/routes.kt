package com.example.githubsearchapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object SearchScreen

@Serializable
data class RepositoryScreenNavArg(val owner: String, val repository: String, val path: String = "")