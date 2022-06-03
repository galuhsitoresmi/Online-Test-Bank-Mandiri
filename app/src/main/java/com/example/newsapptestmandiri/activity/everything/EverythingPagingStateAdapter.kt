package com.example.newsapptestmandiri.activity.everything

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapptestmandiri.databinding.EverythingItemStateBinding

class EverythingPagingStateAdapter(val context: Context):
LoadStateAdapter<EverythingStateViewHolder>(){
    override fun onBindViewHolder(holder: EverythingStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState):
            EverythingStateViewHolder = EverythingStateViewHolder(EverythingItemStateBinding.inflate(
            LayoutInflater.from(context), parent, false)
    ).apply {
        this.bind(loadState)
    }

}

class EverythingStateViewHolder(
    private val binding: EverythingItemStateBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(loadState: LoadState){
        when (loadState) {
            is LoadState.Error -> {
                binding.errorContainer.visibility = View.GONE
                binding.loadingContainer.visibility = View.VISIBLE
            }
            is LoadState.Loading -> {
                binding.errorContainer.visibility = View.GONE
                binding.loadingContainer.visibility = View.VISIBLE
            }
            is LoadState.NotLoading -> {
                binding.errorContainer.visibility = View.GONE
                binding.loadingContainer.visibility = View.GONE
            }
        }
    }
}