package com.example.githubsearchapp.data

import android.util.Log
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.common.Resource.Companion.mapHttpErrorCodeToResourceError
import com.example.githubsearchapp.data.models.RepositoryContentModel
import com.example.githubsearchapp.data.models.RepositoryModel
import com.example.githubsearchapp.data.models.UserModel
import com.example.githubsearchapp.domain.SearchRepository
import com.example.githubsearchapp.domain.models.RepositoryContent
import com.example.githubsearchapp.domain.models.RepositoryContentRequest
import com.example.githubsearchapp.domain.models.RepositoryContentType
import com.example.githubsearchapp.common.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

class SearchRepositoryImpl(private val api: ApiService) : SearchRepository {
    override fun getUsersAndRepositories(searchInput: String): Flow<Resource<List<Any>>> = flow {
        try {
            emit(Resource.loading())

            val usersResponse = api.getUsers(searchInput = searchInput)
            val repositoriesResponse = api.getRepositories(name = searchInput)

            if (usersResponse.isSuccessful && repositoriesResponse.isSuccessful) {

                val usersResponseBody = usersResponse.body()
                val repositoriesResponseBody = repositoriesResponse.body()

                if (usersResponseBody != null && repositoriesResponseBody != null) {

                    val usersData: List<SearchResult.User> = usersResponseBody.items.map {
                        userModelToUser(it)
                    }.toMutableList()

                    val repositoriesData: List<SearchResult.Repository> =
                        repositoriesResponseBody.items.map {
                            repositoryModelToRepository(it)
                        }

                    val data = usersData + repositoriesData

                    val sortedData = sortUsersAndRepositories(data)

                    emit(Resource.success(data = sortedData))
                }
            } else {
                emit(Resource.error(error = mapHttpErrorCodeToResourceError(usersResponse.code())))
                emit(Resource.error(error = mapHttpErrorCodeToResourceError(repositoriesResponse.code())))
            }
        } catch (e: IOException) {
            emit(Resource.error(error = Resource.Error.ERROR_NO_INTERNET_CONNECTION))
        } catch (e: Exception) {
            emit(Resource.error(error = Resource.Error.ERROR_UNDEFINED))
            handleNetworkError(e)
        }
    }.flowOn(Dispatchers.IO)

    override fun getRepositoryContents(requestData: RepositoryContentRequest): Flow<Resource<List<RepositoryContent>>> =
        flow {
            try {

                val response = api.getRepositoryContents(
                    owner = requestData.owner,
                    repository = requestData.repository,
                    path = requestData.path
                )

                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        val repositoryContents =
                            responseBody.map { repositoryContentModelToRepositoryContent(it) }

                        val sortedRepositoryContents = sortRepositoryContents(repositoryContents)

                        emit(Resource.success(data = sortedRepositoryContents))
                    }
                } else {
                    emit(Resource.error(error = mapHttpErrorCodeToResourceError(response.code())))
                }

            } catch (e: IOException) {
                emit(Resource.error(error = Resource.Error.ERROR_NO_INTERNET_CONNECTION))
            } catch (e: Exception) {
                handleNetworkError(e)
            }

        }.flowOn(Dispatchers.IO)

    private fun userModelToUser(userModel: UserModel): SearchResult.User {
        return SearchResult.User(
            login = userModel.login,
            avatarURL = userModel.avatarUrl,
            score = userModel.score,
            htmlURL = userModel.htmlURL
        )
    }

    private fun repositoryModelToRepository(repository: RepositoryModel): SearchResult.Repository {
        return SearchResult.Repository(
            name = repository.name,
            description = repository.description,
            numberOfForks = repository.numberOfForks,
            owner = repository.owner?.let { userModelToUser(it) }
        )
    }

    private fun repositoryContentModelToRepositoryContent(repositoryContentItemModel: RepositoryContentModel): RepositoryContent {
        return RepositoryContent(
            name = repositoryContentItemModel.name,
            type = getRepositoryContentItemType(repositoryContentItemModel),
            path = repositoryContentItemModel.path,
            htmlURL = repositoryContentItemModel.htmlURL
        )
    }

    private fun getRepositoryContentItemType(repositoryContentItemModel: RepositoryContentModel): RepositoryContentType? {
        return when (repositoryContentItemModel.type) {
            "file" -> RepositoryContentType.FILE
            "dir" -> RepositoryContentType.DIR
            else -> null
        }
    }

    private fun handleNetworkError(e: Exception) {
        Log.e("NetworkRequestException", "Exception: $e")
    }

    private fun sortRepositoryContents(repositoryContent: List<RepositoryContent?>): List<RepositoryContent> {
        return repositoryContent.filterNotNull()
            .sortedWith(compareBy { it.type == RepositoryContentType.FILE })
    }

    private fun sortUsersAndRepositories(list: List<Any>): List<Any> {
        return list.sortedBy { item ->
            when (item) {
                is SearchResult.User -> item.login ?: ""
                is SearchResult.Repository -> item.name ?: ""
                else -> ""
            }
        }
    }
}