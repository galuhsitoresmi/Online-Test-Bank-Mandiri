package com.example.newsapptestmandiri.activity.category

import android.content.Intent
import android.view.LayoutInflater
import com.example.newsapptestmandiri.activity.sources.SourcesActivity
import com.example.newsapptestmandiri.activity.sources.SourcesActivity.Constants.EXTRA_SELECTED_CATEGORY
import com.example.newsapptestmandiri.databinding.CategoryListActivityBinding

fun CategoryActivity.initBinding(){
    binding = CategoryListActivityBinding
        .inflate(LayoutInflater.from(this), null,false)
    binding.lifecycleOwner=this
    setContentView(binding.root)

    binding.recyclerCategory.adapter = adapter

    binding.nextBtn.setOnClickListener {
        val selectedCategory = getSelectedCategory()
        val selectedCategoryIds = ArrayList<String>()
        selectedCategoryIds.addAll(selectedCategory.map { it })
        val intent = Intent(this, SourcesActivity::class.java )
        intent.putStringArrayListExtra(EXTRA_SELECTED_CATEGORY, selectedCategoryIds)
        startActivity(intent)
    }

}

fun CategoryActivity.observeLiveData(){
    vm.categoryData.observe(this){
        adapter.sendData(it)
    }
}

fun CategoryActivity.getSelectedCategory() = vm.selectedCategory