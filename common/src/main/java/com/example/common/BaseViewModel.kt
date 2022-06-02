package com.example.common

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.example.common.ext.collectCatching
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val navigationEvent = SingleLiveEvent<Intent>()
    open fun handleNavigationParams(it: Intent){

    }

    fun <T> observeResourceFlow(
        flow: Flow<T>,
        error: ((Exception) -> Unit)? = null,
        block: (T) -> Unit
        ){
        CoroutineScope(Dispatchers.IO).launch {
            flow.collectCatching({
                runBlocking(Dispatchers.Main) {
                    block(it)
                }
            }) {
                error?.invoke(it)
            }
        }
    }

}