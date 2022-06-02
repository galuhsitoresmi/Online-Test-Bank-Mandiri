package com.example.api_service.usecase

import com.example.api_service.service.SourcesService
import com.example.common.entity.base_response.AppResponse
import com.example.common.entity.sources.SourcesResponse
import kotlinx.coroutines.flow.flow

class SourcesUseCase(private val sourcesService: SourcesService) {
    operator fun invoke(category: String) = flow {
       try {
           emit(AppResponse.loading())
           val data = sourcesService.getSources(category= category)
           if (data.isSuccessful){
               data.body()?.let {
                   emit(AppResponse.success(it))
               } ?: run {
                   emit(AppResponse.errorBackEnd<SourcesResponse>(data.code(), data.errorBody()))
               }
           } else {
               emit(AppResponse.errorBackEnd(data.code(), data.errorBody()))
           }

       } catch (e:Exception){
           emit(AppResponse.errorSystem(e))
       }
    }
}