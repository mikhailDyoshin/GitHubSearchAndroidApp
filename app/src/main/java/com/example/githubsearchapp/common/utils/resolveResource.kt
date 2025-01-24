package com.example.githubsearchapp.common.utils

import com.example.githubsearchapp.common.Resource

fun <T> resolveResource(
    resource: Resource<List<T>>,
    onSuccess: (resource: Resource<List<T>>) -> Unit,
    onError: (resource: Resource.Error?) -> Unit,
    onLoading: () -> Unit,
) {
    when (resource.status) {
        Resource.Status.SUCCESS -> {
            onSuccess(resource)
        }

        Resource.Status.ERROR -> {
            onError(resource.error)
        }

        Resource.Status.LOADING -> {
            onLoading()
        }
    }
}