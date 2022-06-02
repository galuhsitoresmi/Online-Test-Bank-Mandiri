package com.example.common.entity.base_response

import okhttp3.ResponseBody

sealed class AppResponse<T>(
    val data: T?, val error: Exception?, val code: Int?, val errorBody: ResponseBody?) {
    companion object {
        fun <T> success(t: T) : AppResponse<T> = ResponseSuccess(t)
        fun <T> loading() : AppResponse<T> = ResponseLoading()
        fun <T> errorSystem(exc: Exception) : AppResponse<T> =
            ResponseErrorSystem(exc)
        fun <T> errorBackEnd(statusCode: Int, body: ResponseBody?) : AppResponse<T> =
            ResponseErrorBackEnd(statusCode, body)
    }
}

class ResponseSuccess<T>(data : T) : AppResponse<T>(data, null, null, null)
class ResponseLoading<T> : AppResponse<T>(null, null, null, null)
class ResponseErrorSystem<T>(e : Exception) : AppResponse<T>(null,e, null, null)
class ResponseErrorBackEnd<T>(code: Int?, body:ResponseBody?) : AppResponse<T>(null, null, code, body)