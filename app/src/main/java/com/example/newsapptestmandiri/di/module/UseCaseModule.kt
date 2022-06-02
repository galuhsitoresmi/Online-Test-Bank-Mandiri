package com.example.newsapptestmandiri.di.module

import com.example.api_service.service.EverythingService
import com.example.api_service.service.SourcesService
import com.example.api_service.usecase.CategoryUseCase
import com.example.api_service.usecase.EverythingPagingUseCase
import com.example.api_service.usecase.SourcesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideCategoryUseCase() = CategoryUseCase()

    @Provides
    fun provideSourcesUseCase(sourceService : SourcesService) =
        SourcesUseCase(sourceService)

    @Provides
    fun provideEverythingPagingUseCase(everythingServices: EverythingService) =
        EverythingPagingUseCase(everythingServices)


}