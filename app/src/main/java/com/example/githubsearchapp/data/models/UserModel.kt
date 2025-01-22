package com.example.githubsearchapp.data.models

import com.squareup.moshi.Json

data class UserModel(
    val login: String?,
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    val score: Float?,
    @Json(name = "html_url")
    val htmlURL: String?
)
