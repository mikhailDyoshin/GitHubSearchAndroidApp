package com.example.githubsearchapp.data.models

import com.squareup.moshi.Json

data class SearchRepositoriesResponseModel(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    val items: List<RepositoryModel>
)
