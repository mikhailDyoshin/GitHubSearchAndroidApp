package com.example.githubsearchapp.presentation.repositoryContentScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.data.SearchRepositoryImpl
import com.example.githubsearchapp.domain.models.RepositoryContent
import com.example.githubsearchapp.domain.models.RepositoryContentRequest
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg
import com.example.githubsearchapp.presentation.repositoryContentScreen.state.RepositoryContentItemState
import com.example.githubsearchapp.presentation.repositoryContentScreen.state.RepositoryContentState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RepositoryContentScreenViewModel(private val repository: SearchRepositoryImpl) : ViewModel() {

    private val _requestDataState: MutableSharedFlow<RepositoryScreenNavArg> = MutableSharedFlow(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state = _requestDataState.map { requestData ->
        getMappedFlow(requestData)
    }.flattenConcat().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), RepositoryContentState(
            repositoryContent = emptyList(),
            status = Resource.Status.SUCCESS
        )
    )

    fun getContent(requestData: RepositoryScreenNavArg) {
        viewModelScope.launch {
            _requestDataState.emit(requestData)
        }
    }

    private fun getMappedFlow(requestDataState: RepositoryScreenNavArg): Flow<RepositoryContentState> {

       val flow = repository.getRepositoryContents(
           requestData = RepositoryContentRequest(
               owner = requestDataState.owner,
               repository = requestDataState.repository,
               path = requestDataState.path
           )
       )


        val mappedFlow = flow.map { resource ->
            mapToState(resource)
        }

        return mappedFlow
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
                    htmlURL = item.htmlURL
                )
            },
            status = Resource.Status.SUCCESS
        )
    }

    private fun mapToState(resource: Resource<List<RepositoryContent>>): RepositoryContentState {
        return when (resource.status) {
            Resource.Status.SUCCESS -> mapToSuccessState(resource)

            Resource.Status.ERROR -> {
                RepositoryContentState(
                    repositoryContent = emptyList(),
                    status = Resource.Status.ERROR,
                    message = resource.error?.message ?: ""
                )
            }

            Resource.Status.LOADING -> {
                RepositoryContentState(
                    repositoryContent = emptyList(),
                    status = Resource.Status.LOADING
                )
            }
        }
    }

}