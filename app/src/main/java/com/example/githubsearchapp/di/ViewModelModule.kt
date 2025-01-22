package com.example.githubsearchapp.di

import com.example.githubsearchapp.presentation.searchScreen.SearchScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SearchScreenViewModel(get())
    }
}