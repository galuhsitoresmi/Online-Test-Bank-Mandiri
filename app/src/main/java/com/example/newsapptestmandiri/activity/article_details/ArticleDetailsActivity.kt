package com.example.newsapptestmandiri.activity.article_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.common.BaseActivity
import com.example.newsapptestmandiri.R
import com.example.newsapptestmandiri.databinding.ArticleDetailsActivityBinding


class ArticleDetailsActivity: BaseActivity<ArticleDetailsActivityBinding>() {
    override val layoutResourceId: Int = R.layout.article_details_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraUrl = intent.getStringExtra("EXTRA_DATA").toString()
        binding.articleWebView.loadUrl(extraUrl)
    }
}