package com.example.newsapptestmandiri.activity.everything

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.entity.everything.Article
import com.example.newsapptestmandiri.activity.article_details.ArticleDetailsActivity
import com.example.newsapptestmandiri.databinding.EverythingItemBinding

class EverythingPagingAdapter(
    private val context: Context
) : PagingDataAdapter<Article, EverythingViewHolder>(differEverything) {

    val everythingData = AsyncListDiffer(this, differEverything)
    override fun onBindViewHolder(holder: EverythingViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EverythingViewHolder =
        EverythingViewHolder(parent.context, EverythingItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = everythingData.currentList.size

    companion object {
        val differEverything = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }


}

class EverythingViewHolder(
    private val context: Context,
    private val binding: EverythingItemBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(data: Article){
        binding.articleTitle.text = data.title
        binding.articleDescription.text = data.description
        Glide.with(binding.articleImage).load(data.urlToImage).into(binding.articleImage)
        binding.root.setOnClickListener {
            val intent  = Intent(context, ArticleDetailsActivity::class.java)
            intent.putExtra("EXTRA_DATA", data.url)
            context.startActivity(intent)
        }
    }
}