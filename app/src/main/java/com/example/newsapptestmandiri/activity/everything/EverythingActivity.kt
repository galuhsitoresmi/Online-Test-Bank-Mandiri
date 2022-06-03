package com.example.newsapptestmandiri.activity.everything

import android.os.Bundle
import androidx.activity.viewModels
import com.example.common.BaseActivity
import com.example.newsapptestmandiri.R
import com.example.newsapptestmandiri.databinding.EverythingActivityBinding
import com.example.newsapptestmandiri.view_model.EverythingViewModel

class EverythingActivity : BaseActivity<EverythingActivityBinding>() {
    override val layoutResourceId: Int = R.layout.everything_activity

    val vm: EverythingViewModel by viewModels {
        vmFactory
    }

    val adapter = EverythingPagingAdapter(this)
    val loadStateAadpter = EverythingPagingStateAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initbinding()
        observeLiveData()
    }

    object Constants {
        const val EXTRA_SELECTED_SOURCES = "EXTRA_SELECTED_SOURCES"
    }
}