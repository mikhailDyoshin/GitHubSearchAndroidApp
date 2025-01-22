package com.example.githubsearchapp.di

import com.example.githubsearchapp.data.SearchRepositoryImpl
import com.example.githubsearchapp.domain.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
//    single<SearchRepository> { SearchRepositoryImpl(get()) }
    factory { SearchRepositoryImpl(get()) }
}