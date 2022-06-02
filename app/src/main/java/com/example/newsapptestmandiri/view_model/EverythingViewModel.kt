package com.example.newsapptestmandiri.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.api_service.usecase.EverythingPagingUseCase
import com.example.common.BaseViewModel
import com.example.common.entity.base_response.AppResponse
import com.example.common.entity.base_response.ResponseSuccess
import com.example.common.entity.everything.Article
import com.example.common.entity.everything.EverythingResponse
import kotlinx.coroutines.launch

class EverythingViewModel(
    application: Application,
    val everythingPagingUseCase: EverythingPagingUseCase
) : BaseViewModel(application) {
    val pagingDataEverything = MutableLiveData<PagingData<Article>>()
    val everythingState = MutableLiveData<AppResponse<EverythingResponse>>()

    fun getEverything(sources : ArrayList<String>, q:String){
        viewModelScope.launch {
            everythingPagingUseCase.invoke(sources, q).collect{
                pagingDataEverything.postValue(it)
            }
        }
    }

    fun filter(q: String): List<Article>? {
        return everythingState.value.let {
            if (it is ResponseSuccess){
                it.data?.articles.orEmpty().filter {
                    it.title.lowercase().contains(q.lowercase())
                }
            }else arrayListOf()
        }?: run{
            arrayListOf()
        }
    }
}