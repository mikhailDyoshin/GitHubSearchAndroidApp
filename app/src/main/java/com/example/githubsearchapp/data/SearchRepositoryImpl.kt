package com.example.githubsearchapp.data

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.SearchRepository
import com.example.githubsearchapp.domain.models.RepositoryContent
import com.example.githubsearchapp.domain.models.RepositoryContentRequest
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val api: ApiService) : SearchRepository {
    override fun getUsersAndRepositories(searchInput: String): Flow<Resource<List<Any>>> {
        TODO("Not yet implemented")
    }

    override fun getRepositoryContents(requestData: RepositoryContentRequest): Flow<Resource<List<RepositoryContent>>> {
        TODO("Not yet implemented")
    }
}