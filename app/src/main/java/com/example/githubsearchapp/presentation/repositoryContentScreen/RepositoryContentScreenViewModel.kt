package com.example.githubsearchapp.presentation.repositoryContentScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.common.utils.resolveResource
import com.example.githubsearchapp.data.SearchRepositoryImpl
import com.example.githubsearchapp.domain.models.RepositoryContent
import com.example.githubsearchapp.domain.models.RepositoryContentRequest
import com.example.githubsearchapp.domain.models.RepositoryContentType
import com.example.githubsearchapp.domain.usecase.TransformFileSizeUseCase
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg
import com.example.githubsearchapp.presentation.repositoryContentScreen.state.RepositoryContentItemState
import com.example.githubsearchapp.presentation.repositoryContentScreen.state.RepositoryContentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RepositoryContentScreenViewModel(private val repository: SearchRepositoryImpl) : ViewModel() {

    val transformFileSizeUseCase = TransformFileSizeUseCase()

    private val _state: MutableStateFlow<RepositoryContentState> = MutableStateFlow(
        RepositoryContentState(
            repositoryContent = emptyList(),
            status = Resource.Status.SUCCESS
        )
    )
    val state: StateFlow<RepositoryContentState> = _state

    fun getContent(requestData: RepositoryScreenNavArg) {
        viewModelScope.launch {
            getMappedFlow(requestData)
        }
    }

    private fun getMappedFlow(requestDataState: RepositoryScreenNavArg) {

       val flow = repository.getRepositoryContents(
           requestData = RepositoryContentRequest(
               owner = requestDataState.owner,
               repository = requestDataState.repository,
               path = requestDataState.path
           )
       )


        flow.onEach {  updateState(it) }.launchIn(viewModelScope)

    }

    private fun mapToSuccessState(resource: Resource<List<RepositoryContent>>): RepositoryContentState {
        if (resource.data == null) return RepositoryContentState(
            repositoryContent = emptyList(),
            status = Resource.Status.SUCCESS
        )

        return RepositoryContentState(
            repositoryContent = resource.data.map { item ->
                RepositoryContentItemState(
                    name = item.name,
                    type = item.type,
                    path = item.path,
                    htmlURL = item.htmlURL,
                    sizeInBytes = when (item.type) {
                        RepositoryContentType.FILE -> transformFileSizeUseCase(item.sizeInBytes)
                        RepositoryContentType.DIR -> null
                        null -> null
                    }
                )
            },
            status = Resource.Status.SUCCESS
        )
    }

    private fun updateState(resource: Resource<List<RepositoryContent>>) {
        resolveResource(
            resource = resource,
            onSuccess = {
                val listState = mapToSuccessState(it)
                _state.value = listState
            },
            onError = {
                val listState = RepositoryContentState(
                    repositoryContent = emptyList(),
                    status = Resource.Status.ERROR,
                    message = resource.error?.message ?: ""
                )
                _state.value = listState
            },
            onLoading = {
                val listState = RepositoryContentState(
                    repositoryContent = emptyList(),
                    status = Resource.Status.LOADING
                )
                _state.value = listState
            }
        )
    }

}