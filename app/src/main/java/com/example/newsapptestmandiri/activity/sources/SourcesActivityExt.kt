package com.example.newsapptestmandiri.activity.sources

import android.content.Intent
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import com.example.newsapptestmandiri.activity.everything.EverythingActivity
import com.example.newsapptestmandiri.activity.everything.EverythingActivity.Constants.EXTRA_SELECTED_SOURCES
import com.example.newsapptestmandiri.databinding.SourcesActivityBinding

fun SourcesActivity.initBinding(){
    binding = SourcesActivityBinding.inflate(
        LayoutInflater.from(this), null, false)
    binding.lifecycleOwner=this
    setContentView(binding.root)

    binding.sourceRecycler.adapter = adapter

    binding.sourceSearch.addTextChangedListener {
        adapter.sourcesData.submitList(vm.filter(it.toString()))
    }

    binding.nextBtn.setOnClickListener {
        val selectedSources = getSelectedSources()
        val selectedSourcesIds = ArrayList<String>()
        selectedSourcesIds.addAll(selectedSources.map { it.id })
        val intent = Intent(this, EverythingActivity::class.java )
        intent.putStringArrayListExtra(EXTRA_SELECTED_SOURCES, selectedSourcesIds)
        startActivity(intent)
    }
}

fun SourcesActivity.observeLiveData(){
    vm.sourceState.observe(this){
        adapter.sendDataSources(it.data?.sources.orEmpty())

    }

    val selectedCategory = intent.getStringArrayListExtra(
        SourcesActivity.Constants.EXTRA_SELECTED_CATEGORY)
    vm.getSources(selectedCategory?.get(0).orEmpty())

}

fun SourcesActivity.getSelectedSources() = vm.selectedSources