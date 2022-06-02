package com.example.common

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.ViewModelProviderFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<Binding: ViewDataBinding> : AppCompatActivity(){
    lateinit var binding: Binding
    abstract val layoutResourceId : Int


    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            layoutResourceId,
            null,
            false
        )
        binding.lifecycleOwner = this
        setContentView(binding.root)


    }
}