package com.example.githubsearchapp.data

import com.example.githubsearchapp.data.models.RepositoryContentModel
import com.example.githubsearchapp.data.models.SearchRepositoriesResponseModel
import com.example.githubsearchapp.data.models.SearchUsersResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

interface ApiService {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") searchInput: String
    ): Response<SearchUsersResponseModel>

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") name: String
    ): Response<SearchRepositoriesResponseModel>

    @GET("repos/{owner}/{repo}/contents/{path}")
    suspend fun getRepositoryContents(
        @Path("owner") owner: String,
        @Path("repo") repository: String,
        @Path("path") path: String
    ): Response<List<RepositoryContentModel>>

}