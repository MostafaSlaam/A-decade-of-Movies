package com.task.a_decade_of_movies.core

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failure(val message: String? = null, val uiText: UiText? = null) :
        DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Init : DataState<Nothing>()

    //data class ExceptionState(val exception:): DataState<Nothing>()
}