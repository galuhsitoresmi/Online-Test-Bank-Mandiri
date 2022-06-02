package com.example.newsapptestmandiri.activity.category

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.common.BaseActivity
import com.example.newsapptestmandiri.R
import com.example.newsapptestmandiri.activity.sources.SourcesActivity
import com.example.newsapptestmandiri.databinding.CategoryListActivityBinding
import com.example.newsapptestmandiri.view_model.CategoryViewModel

class CategoryActivity : BaseActivity<CategoryListActivityBinding>() {
    override val layoutResourceId: Int = R.layout.category_list_activity

    val vm: CategoryViewModel by viewModels {
        vmFactory
    }
    val adapter : CategoryAdapter = CategoryAdapter(::getSelectedCategory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observeLiveData()
        vm.getCategory()
    }


}