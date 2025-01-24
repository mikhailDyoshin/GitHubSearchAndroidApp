package com.example.githubsearchapp.presentation.searchScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchapp.R
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.common.SearchResult
import com.example.githubsearchapp.data.SearchRepositoryImpl
import com.example.githubsearchapp.domain.usecase.ParseDateTimeUseCase
import com.example.githubsearchapp.presentation.searchScreen.state.DescriptionState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState
import com.example.githubsearchapp.presentation.searchScreen.state.StatisticState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class SearchScreenViewModel(private val repository: SearchRepositoryImpl) : ViewModel() {

    private val parseDateTimeUseCase = ParseDateTimeUseCase()

    private val _searchInputState = mutableStateOf("")
    val searchInputState: State<String> = _searchInputState

    private val _submittedSearchInput: MutableSharedFlow<String?> = MutableSharedFlow(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state = _submittedSearchInput.map { input ->
        getMappedFlow(input)
    }.flattenConcat().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), SearchScreenListState(
            list = emptyList(),
            status = Resource.Status.SUCCESS,
            message = ""
        )
    )

    fun search() {
        viewModelScope.launch {
            _submittedSearchInput.emit(_searchInputState.value)
        }
    }

    fun updateSearchInput(searchInput: String) {
        _searchInputState.value = searchInput
    }

    private fun getMappedFlow(searchInput: String?): Flow<SearchScreenListState> {
        val flow = if (searchInput != null) {
            repository.getUsersAndRepositories(searchInput)
        } else {
            emptyFlow()
        }

        val mappedFlow = flow.map { resource ->
            mapToState(resource)
        }

        return mappedFlow
    }


    private fun mapToSuccessState(resource: Resource<List<Any>>): SearchScreenListState {

        if (resource.data == null) return SearchScreenListState(
            list = emptyList(),
            status = Resource.Status.SUCCESS,
            message = "No items found"
        )

        return SearchScreenListState(list = resource.data.map { item ->

            when (item) {
                is SearchResult.Repository -> {
                    SearchListItemState.RepositoryState(
                        name = item.name ?: "No name",
                        description = DescriptionState(
                            author = SearchListItemState.UserState(
                                name = item.owner?.login ?: "No name",
                                avatarURL = item.owner?.avatarURL ?: "",
                                htmlURL = item.owner?.htmlURL
                            ),
                            created = parseDateTimeUseCase(item.created).toString(),
                            updated = parseDateTimeUseCase(item.updated).toString(),
                            description = item.description ?: ""
                        ),
                        owner = item.owner?.login,
                        statistics = listOf(
                            StatisticState(
                                iconId = R.drawable.star_icon,
                                value = item.stars ?: 0
                            ),
                            StatisticState(
                                iconId = R.drawable.eye_icon,
                                value = item.watchers ?: 0
                            ),
                            StatisticState(iconId = R.drawable.fork_icon, value = item.forks ?: 0)
                        ),
                    )
                }

                is SearchResult.User -> {
                    SearchListItemState.UserState(
                        name = item.login ?: "No name",
                        avatarURL = item.avatarURL ?: "",
                        htmlURL = item.htmlURL
                    )
                }

                else -> {
                    null
                }
            }


        }, status = Resource.Status.SUCCESS)

    }

    private fun mapToState(resource: Resource<List<Any>>): SearchScreenListState {
        return when (resource.status) {
            Resource.Status.SUCCESS -> {
                mapToSuccessState(resource)
            }

            Resource.Status.ERROR -> {
                SearchScreenListState(
                    list = emptyList(),
                    status = Resource.Status.ERROR,
                    message = resource.error?.message ?: ""
                )
            }

            Resource.Status.LOADING -> {
                SearchScreenListState(
                    list = emptyList(),
                    status = Resource.Status.LOADING
                )
            }
        }
    }

}