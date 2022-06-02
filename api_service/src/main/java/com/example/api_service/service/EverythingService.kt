package com.example.api_service.service

import com.example.api_service.Constants
import com.example.common.entity.everything.EverythingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EverythingService {
    @GET("everything")
    suspend fun getEverything (
        @Query("apiKey") apiKey : String = Constants.API_KEY,
        @Query("q") q : String? = null,
        @Query("sources") sources : String? = null,
        @Query("pageSize") pageSize : Int = Constants.DEFAULT_PAGE_SIZE,
        @Query("page") page : Int = 1
    ) : Response<EverythingResponse>
}