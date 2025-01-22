package com.example.githubsearchapp.domain

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.models.RepositoryContent
import com.example.githubsearchapp.domain.models.RepositoryContentRequest
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUsersAndRepositories(searchInput: String): Flow<Resource<List<Any>>>

    fun getRepositoryContents(requestData: RepositoryContentRequest): Flow<Resource<List<RepositoryContent>>>
}