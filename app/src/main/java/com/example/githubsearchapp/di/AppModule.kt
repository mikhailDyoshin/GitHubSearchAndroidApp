package com.example.githubsearchapp.di

import com.example.githubsearchapp.data.SearchRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    factory { SearchRepositoryImpl(get()) }
}