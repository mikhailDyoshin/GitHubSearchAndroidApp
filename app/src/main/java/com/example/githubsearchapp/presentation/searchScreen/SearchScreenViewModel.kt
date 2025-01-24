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
import com.example.githubsearchapp.common.utils.resolveResource
import com.example.githubsearchapp.data.SearchRepositoryImpl
import com.example.githubsearchapp.domain.usecase.ParseDateTimeUseCase
import com.example.githubsearchapp.presentation.searchScreen.state.DescriptionState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState
import com.example.githubsearchapp.presentation.searchScreen.state.StatisticState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@RequiresApi(Build.VERSION_CODES.O)
class SearchScreenViewModel(private val repository: SearchRepositoryImpl) : ViewModel() {

    private val parseDateTimeUseCase = ParseDateTimeUseCase()

    private val _searchInputState = mutableStateOf("")
    val searchInputState: State<String> = _searchInputState


    private val _listState: MutableStateFlow<SearchScreenListState> = MutableStateFlow(
        SearchScreenListState(
            list = emptyList(),
            status = Resource.Status.SUCCESS,
            message = ""
        )
    )
    val listState: StateFlow<SearchScreenListState> = _listState

    fun search() {
        getMappedFlow(_searchInputState.value)
    }

    fun updateSearchInput(searchInput: String) {
        _searchInputState.value = searchInput
    }

    private fun getMappedFlow(searchInput: String?) {
        val flow = if (searchInput != null) {
            repository.getUsersAndRepositories(searchInput)
        } else {
            emptyFlow()
        }

        flow.onEach { updateState(it) }.launchIn(viewModelScope)

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

    private fun updateState(resource: Resource<List<Any>>) {
        resolveResource(
            resource = resource,
            onSuccess = {
                val listState = mapToSuccessState(it)
                _listState.value = listState
            },
            onError = {
                val listState = SearchScreenListState(
                    list = emptyList(),
                    status = Resource.Status.ERROR,
                    message = resource.error?.message ?: ""
                )
                _listState.value = listState
            },
            onLoading = {
                val listState = SearchScreenListState(
                    list = emptyList(),
                    status = Resource.Status.LOADING
                )
                _listState.value = listState
            }
        )
    }
}