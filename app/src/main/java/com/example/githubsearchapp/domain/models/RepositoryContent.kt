package com.example.githubsearchapp.domain.models

data class RepositoryContent(
    val name: String?,
    val type: RepositoryContentType?,
    val path: String?,
    val htmlURL: String?,
    val sizeInBytes: Int?
)
