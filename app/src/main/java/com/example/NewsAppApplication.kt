package com.example

import com.example.newsapptestmandiri.di.module.ApiModule
import com.example.newsapptestmandiri.di.module.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NewsAppApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .application(this)
            .app(ApiModule(this))
            .build()
}