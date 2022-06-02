package com.example.newsapptestmandiri.activity.category

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.entity.everything.Article
import com.example.newsapptestmandiri.databinding.CategoryListItemBinding

class CategoryAdapter (
    private val getSelectedCategory: () -> ArrayList<String>
) : RecyclerView.Adapter<CategoryViewHolder>(){
    val listData = AsyncListDiffer<String>(this, differCategory)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(CategoryListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false), ::onRowClicked)

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listData.currentList[position], getSelectedCategory)
    }

    override fun getItemCount(): Int = listData.currentList.size

    fun sendData(categories: List<String>){
        listData.submitList(categories)
    }

    private fun onRowClicked(data: String, position: Int){
        if (getSelectedCategory().contains(data)){
            getSelectedCategory().remove(data)
        } else {
            getSelectedCategory().add(data)
        }
        notifyItemChanged(position)
    }

    companion object {
        val differCategory = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return true
            }
        }
    }


}

class CategoryViewHolder(
    val binding: CategoryListItemBinding,
    val onRowClickedListener:(String, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String,
             getSelectedCategory: () -> ArrayList<String>) {
        binding.categoryText.text = data
        binding.root.setOnClickListener {
            onRowClickedListener(data, position)
        }
        if (data in getSelectedCategory()) {
            binding.categoryCv.setCardBackgroundColor(Color.BLUE)
            binding.categoryText.setTextColor(Color.WHITE)
        } else {
            binding.categoryCv.setCardBackgroundColor((Color.WHITE))
            binding.categoryText.setTextColor(Color.BLACK)
        }
    }
    }