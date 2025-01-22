package com.example.githubsearchapp.domain.models

data class RepositoryContentRequest(val owner: String, val repository: String, val path: String)
