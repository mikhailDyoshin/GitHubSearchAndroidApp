package com.example.githubsearchapp.data.models

import com.squareup.moshi.Json

data class RepositoryModel(
    val name: String?,
    val description: String?,
    @Json(name = "forks_count")
    val numberOfForks: Int?,
    val owner: UserModel?,
    val watchers: Int?,
    val forks: Int?,
    @Json(name = "stargazers_count")
    val stars: Int?,
    @Json(name = "created_at")
    val created: String?,
    @Json(name = "updated_at")
    val updated: String?
)
