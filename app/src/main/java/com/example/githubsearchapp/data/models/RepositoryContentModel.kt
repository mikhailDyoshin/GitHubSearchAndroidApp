package com.example.githubsearchapp.data.models

import com.squareup.moshi.Json

data class RepositoryContentModel(
    val type: String?,
    val name: String?,
    val path: String?,
    @Json(name = "html_url")
    val htmlURL: String?,
    val size: Int,
)
