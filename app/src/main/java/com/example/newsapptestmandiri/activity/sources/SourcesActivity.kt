package com.example.newsapptestmandiri.activity.sources

import android.os.Bundle
import androidx.activity.viewModels
import com.example.common.BaseActivity
import com.example.newsapptestmandiri.R
import com.example.newsapptestmandiri.databinding.SourcesActivityBinding
import com.example.newsapptestmandiri.view_model.SourcesViewModel

class SourcesActivity : BaseActivity<SourcesActivityBinding>() {
    override val layoutResourceId: Int = R.layout.category_list_item

    val vm : SourcesViewModel by viewModels {
        vmFactory
    }
     val adapter : SourcesAdapter = SourcesAdapter(::getSelectedSources)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observeLiveData()

    }

    object Constants {
        const val EXTRA_SELECTED_CATEGORY : String = "SELECTED_CATEGORY"
    }
}