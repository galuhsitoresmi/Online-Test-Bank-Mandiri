package com.example.newsapptestmandiri.di.module

import android.app.Application
import com.example.api_service.usecase.CategoryUseCase
import com.example.api_service.usecase.SourcesUseCase
import com.example.newsapptestmandiri.view_model.CategoryViewModel
import com.example.newsapptestmandiri.view_model.SourcesViewModel
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication

@Module(includes = [ApiModule::class])
class ViewModelModule {

    @Provides
    fun provideCategoryListViewModel(application: DaggerApplication,
                                     categoryUseCase: CategoryUseCase
    ) : CategoryViewModel {
        return CategoryViewModel(application, categoryUseCase)
    }

    @Provides
    fun provideSourcesViewModel(application: DaggerApplication,
                                sourcesUseCase: SourcesUseCase
    ): SourcesViewModel {
        return SourcesViewModel(application, sourcesUseCase)
    }


}