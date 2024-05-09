package com.task.a_decade_of_movies.core


class ResponseDto<T : Any?> {

    var data: T? = null


    var message: String = ""


    var status: Int = 0
}