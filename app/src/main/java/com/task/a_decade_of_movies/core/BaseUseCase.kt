package com.task.a_decade_of_movies.core

interface BaseUseCase<In, Out>{
    suspend fun execute(input: In): Out
}