package com.task.a_decade_of_movies.core

sealed class DataStatePaging<out R> {
    data class Success<out T>(val data: T) : DataStatePaging<T>()
    data class Failure(val message: String? = null, val uiText: UiText? = null) :
        DataStatePaging<Nothing>()
    object Loading : DataStatePaging<Nothing>()
    object NextLoading : DataStatePaging<Nothing>()
    object Init : DataStatePaging<Nothing>()

    //data class ExceptionState(val exception:): DataState<Nothing>()
}