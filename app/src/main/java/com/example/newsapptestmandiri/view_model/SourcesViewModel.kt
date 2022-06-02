package com.example.newsapptestmandiri.view_model

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.api_service.usecase.SourcesUseCase
import com.example.common.BaseViewModel
import com.example.common.entity.base_response.AppResponse
import com.example.common.entity.base_response.ResponseSuccess
import com.example.common.entity.sources.Source
import com.example.common.entity.sources.SourcesResponse
import kotlinx.coroutines.launch

class SourcesViewModel(
    application: Application,
    val sourcesUseCase: SourcesUseCase
) : BaseViewModel(application) {
    val sourceState = MutableLiveData<AppResponse<SourcesResponse>>()
    val selectedSources = arrayListOf<Source>()


    fun getSources(category: String) {
        viewModelScope.launch {
            sourcesUseCase.invoke(category).collect {
                sourceState.postValue(it)
            }
        }
    }

    fun filter(q: String): List<Source>? {
        return sourceState.value.let {
            if (it is ResponseSuccess){
                it.data?.sources.orEmpty().filter {
                    it.name.lowercase().contains(q.lowercase())
                }
            }else arrayListOf()
        }?: run{
            arrayListOf()
        }
    }

}