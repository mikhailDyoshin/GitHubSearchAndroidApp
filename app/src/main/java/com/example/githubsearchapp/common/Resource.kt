package com.example.githubsearchapp.common

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val error: Error?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
    }

    enum class Error(val message: String) {
        ERROR_401(message = "Access is denied"),
        ERROR_403(message = "Requests limit is reached"),
        ERROR_404(message = "404 error"),
        ERROR_500(message = "Server error"),
        ERROR_UNDEFINED(message = "Undefined error"),
        ERROR_NO_INTERNET_CONNECTION(message = "Check internet connection"),
        ERROR_NO_DATA(message = "No data"),
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(status = Status.SUCCESS, data = data, message = null, error = null)
        }

        fun <T> error(error: Error, data: T? = null): Resource<T> {
            return Resource(
                status = Status.ERROR,
                data = data,
                message = error.message,
                error = error
            )
        }

        fun mapHttpErrorCodeToResourceError(code: Int): Error {
            return when (code) {
                401 -> Error.ERROR_401
                403 -> Error.ERROR_403
                404 -> Error.ERROR_404
                500 -> Error.ERROR_500
                else -> Error.ERROR_UNDEFINED
            }
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(status = Status.LOADING, data = data, message = null, error = null)
        }
    }

}
