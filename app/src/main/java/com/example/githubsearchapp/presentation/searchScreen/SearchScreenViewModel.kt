package com.example.githubsearchapp.presentation.searchScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.common.SearchResult
import com.example.githubsearchapp.data.SearchRepositoryImpl
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchScreenViewModel(private val repository: SearchRepositoryImpl) : ViewModel() {

    private val _searchInputState = mutableStateOf("")
    val searchInputState: State<String> = _searchInputState

    private val submittedSearchInput: MutableStateFlow<String?> = MutableStateFlow(null)

    val state = submittedSearchInput.flatMapLatest { input ->
        val flow = if (input != null) {
            repository.getUsersAndRepositories(input)
        } else {
            emptyFlow()
        }
        flow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Resource.success(emptyList())).map { resource ->
            mapToState(resource)
        }
    }



    fun search() {
        viewModelScope.launch {
            submittedSearchInput.emit(_searchInputState.value)
        }
    }

    fun updateSearchInput(searchInput: String) {
        _searchInputState.value = searchInput
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
                            description = item.description ?: "No description",
                            numberOfForks = item.numberOfForks ?: 0,
                            owner = item.owner?.login
                        )
                    }

                    is SearchResult.User -> {
                        SearchListItemState.UserState(
                            name = item.login ?: "No name",
                            avatarURL = item.avatarURL ?: "",
                            score = item.score ?: 0f,
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