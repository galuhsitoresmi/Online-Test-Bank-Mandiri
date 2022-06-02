package com.example.newsapptestmandiri.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.api_service.usecase.CategoryUseCase
import com.example.common.BaseViewModel
import kotlinx.coroutines.launch

class CategoryViewModel(
    application: Application,
    val categoryUseCase: CategoryUseCase
) : BaseViewModel(application){
    val categoryData = MutableLiveData<List<String>>()
    val selectedCategory = ArrayList<String>()

    fun getCategory() {
        viewModelScope.launch {
            categoryUseCase().collect(){
                categoryData.postValue(it)
            }
        }
    }

}