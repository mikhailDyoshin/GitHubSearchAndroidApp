package com.example.githubsearchapp.data.models

import com.squareup.moshi.Json

data class RepositoryModel(
    val name: String?,
    val description: String?,
    @Json(name = "forks_count")
    val numberOfForks: Int?,
    val owner: UserModel?
)
