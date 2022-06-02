package com.example.newsapptestmandiri.di.module

import com.example.NewsAppApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ViewModelFactoryModule::class, UseCaseModule::class])
interface ApplicationComponent : AndroidInjector<NewsAppApplication> {
    @Component.Builder
    interface Builder {
        fun app(apiModule: ApiModule):Builder
        @BindsInstance
        fun application(application: DaggerApplication): Builder
        fun build(): ApplicationComponent
    }
}