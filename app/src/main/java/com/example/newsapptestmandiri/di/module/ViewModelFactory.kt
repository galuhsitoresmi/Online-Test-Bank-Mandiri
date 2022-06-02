package com.example.newsapptestmandiri.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.ViewModelKey
import com.example.common.di.ViewModelProviderFactory
import com.example.newsapptestmandiri.activity.category.CategoryActivity
import com.example.newsapptestmandiri.activity.sources.SourcesActivity
import com.example.newsapptestmandiri.view_model.CategoryViewModel
import com.example.newsapptestmandiri.view_model.SourcesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun provideViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryListViewModel(categoryListViewModel: CategoryViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeCategoryListActivity(): CategoryActivity

    @Binds
    @IntoMap
    @ViewModelKey(SourcesViewModel::class)
    abstract fun bindSourcesViewModel(sourcesViewModel: SourcesViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeSourceActivity(): SourcesActivity



}