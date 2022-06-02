package com.example.api_service.usecase

import kotlinx.coroutines.flow.flow

class CategoryUseCase {
    operator fun invoke() = flow {
        val list = arrayListOf("business",
            "entertainment",
            "general",
            "health",
            "science",
            "sports",
            "technology")
        emit(list)
    }
}