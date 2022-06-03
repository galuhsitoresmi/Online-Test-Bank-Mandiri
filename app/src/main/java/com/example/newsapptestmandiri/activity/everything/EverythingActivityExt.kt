package com.example.newsapptestmandiri.activity.everything

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun EverythingActivity.initbinding(){
    adapter.addLoadStateListener {
        when(it.append){
            is LoadState.Error -> {
                binding.retryButton.visibility = View.VISIBLE
                binding.recyclerEverything.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
            is LoadState.NotLoading -> {
                binding.retryButton.visibility = View.GONE
                binding.recyclerEverything.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }


    binding.recyclerEverything.adapter = adapter.withLoadStateFooter(loadStateAadpter)
    val selectedSources = intent.getStringArrayListExtra(EverythingActivity.Constants.EXTRA_SELECTED_SOURCES)
    binding.retryButton.setOnClickListener {
        selectedSources?.let {
            adapter.retry()
        }
    }

    binding.articleSearch.addTextChangedListener {
        adapter.everythingData.submitList(vm.filter(it.toString()))
    }

}

fun EverythingActivity.observeLiveData(){
    vm.pagingDataEverything.observe(this){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    val selectedSources = intent.getStringArrayListExtra(EverythingActivity.Constants.EXTRA_SELECTED_SOURCES)
    selectedSources?.let {
        vm.getEverything(it, q="")
    }

}