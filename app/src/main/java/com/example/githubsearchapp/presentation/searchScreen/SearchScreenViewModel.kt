package com.example.githubsearchapp.presentation.searchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.data.SearchRepositoryImpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class SearchScreenViewModel(private val repository: SearchRepositoryImpl) : ViewModel() {

    val state = repository.getUsersAndRepositories("Kotlin")
        .stateIn(viewModelScope, SharingStarted.Eagerly, Resource.loading())

}