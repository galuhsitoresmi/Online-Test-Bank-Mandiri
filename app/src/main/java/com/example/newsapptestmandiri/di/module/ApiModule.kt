package com.example.newsapptestmandiri.di.module

import android.content.Context
import com.example.api_service.RetrofitClient
import com.example.api_service.service.EverythingService
import com.example.api_service.service.SourcesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule(val application: Context) {
    @Provides
    @Singleton
    fun provideRetrofit() = RetrofitClient.getClient(application)

    @Provides
    @Singleton
    fun provideSourcesService(retrofit: Retrofit) = retrofit.create(SourcesService::class.java)

    @Provides
    @Singleton
    fun provideEverythingService(retrofit: Retrofit) = retrofit.create(EverythingService::class.java)


}