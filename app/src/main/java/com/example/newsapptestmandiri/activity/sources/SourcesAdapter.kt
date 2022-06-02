package com.example.newsapptestmandiri.activity.sources

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.entity.sources.Source
import com.example.newsapptestmandiri.databinding.SourcesItemBinding

class SourcesAdapter(
    private val getSelectedSources : () -> ArrayList<Source>
): RecyclerView.Adapter<SourceViewHolder>() {
    val sourcesData = AsyncListDiffer(this, differSources)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder =
        SourceViewHolder(SourcesItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false),
            ::onRowClicked)

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(sourcesData.currentList[position], getSelectedSources)
    }

    override fun getItemCount(): Int = sourcesData.currentList.size

    fun sendDataSources(list: List<Source>){
        sourcesData.submitList(list)
    }

    private fun onRowClicked(data: Source, position: Int){
        if (getSelectedSources().contains(data)){
            getSelectedSources().remove(data)
        } else {
            getSelectedSources().add(data)
        }
        notifyItemChanged(position)
    }

    companion object{
        val differSources = object: DiffUtil.ItemCallback<Source>() {
            override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class SourceViewHolder(
    private val binding: SourcesItemBinding,
    val onRowClickedListener: (Source, Int) -> Unit
) : RecyclerView.ViewHolder(binding.root){
    fun bind(source: Source, getSelectedSources: () -> ArrayList<Source>){
        binding.sourcesText.text = source.name
        binding.root.setOnClickListener {
            onRowClickedListener(source, position)
        }
        if(source in getSelectedSources()) {
            binding.sourceCv.setCardBackgroundColor(Color.BLUE)
            binding.sourcesText.setTextColor(Color.WHITE)
        } else {
            binding.sourceCv.setCardBackgroundColor((Color.WHITE))
            binding.sourcesText.setTextColor(Color.BLACK)
        }

    }
}