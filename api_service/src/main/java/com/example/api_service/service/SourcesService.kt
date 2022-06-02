package com.example.api_service.service

import com.example.api_service.Constants
import com.example.common.entity.everything.EverythingResponse
import com.example.common.entity.sources.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SourcesService {
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") api_key: String = Constants.API_KEY,
        @Query("category") category: String? = null
    ) : Response<SourcesResponse>
}