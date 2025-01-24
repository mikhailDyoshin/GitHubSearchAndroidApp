package com.example.githubsearchapp.common

sealed class SearchResult {
    data class Repository(
        val name: String?,
        val description: String?,
        val numberOfForks: Int?,
        val owner: User?,
        val watchers: Int?,
        val forks: Int?,
        val stars: Int?,
        val created: String?,
        val updated: String?
    )

    data class User(
        val login: String?,
        val avatarURL: String?,
        val htmlURL: String?
    )
}