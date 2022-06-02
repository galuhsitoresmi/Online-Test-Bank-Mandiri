package com.example.api_service.usecase

import com.example.api_service.paging.EverythingDataSource
import com.example.api_service.paging.EverythingPager.createPager
import com.example.api_service.service.EverythingService

class EverythingPagingUseCase(
    private val everythingService: EverythingService
) {
    operator fun invoke(args: ArrayList<String>, q: String) =
        EverythingDataSource.createPager(10, everythingService, args, q).flow
}