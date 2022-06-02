package com.example.common.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

fun Boolean?.orFalse() = this ?: false

suspend fun <T> Flow<T>.collectCatching(flowCollector: FlowCollector<T>, err:(Exception) -> Unit) {
    try {
        collect(flowCollector)
    }catch (e:Exception){
        err(e)
    }
}