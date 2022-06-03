package com.example.newsapptestmandiri.activity.everything

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun EverythingActivity.initbinding(){

    adapter.addLoadStateListener {
        if (it.refresh is LoadState.Error && adapter.itemCount == 0){
            binding.retryButton.visibility = View.VISIBLE
            binding.recyclerEverything.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.articleSearch.visibility = View.GONE

        } else if (it.refresh is LoadState.Loading && adapter.itemCount == 0){
            binding.retryButton.visibility = View.GONE
            binding.recyclerEverything.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            binding.articleSearch.visibility = View.GONE
        } else if (it.refresh is LoadState.NotLoading){
            binding.retryButton.visibility = View.GONE
            binding.recyclerEverything.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.articleSearch.visibility = View.VISIBLE
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