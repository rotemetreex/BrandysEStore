package com.rotemyanco.brandysestore.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rotemyanco.brandysestore.databinding.CategoryInfoCardBinding
import com.rotemyanco.brandysestore.models.Category


class CategoryAdapter(
    private val categories: List<Category>,
    private val onItemClicked: OnItemClick<Category>
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    private val logTag = "CategoryAdapter"
    private lateinit var category: Category

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val viewBinding =
            CategoryInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryVH(viewBinding, categories, onItemClicked)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        with(holder.binding) {
            category = categories[position]
            tvSmallTitleCategoryInfoCard.text = category.categoryName
            Log.d(
                logTag,
                "onBindViewHolder:       categoryList[$position] --> ${category.categoryName}"
            )
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryVH(
        val binding: CategoryInfoCardBinding,
        private val categories: List<Category>,
        private val onItemClicked: OnItemClick<Category>
        ) :
        RecyclerView.ViewHolder(binding.root)
    {
            init {
                binding.root.setOnClickListener {
                    onItemClicked.onItemClick(categories[adapterPosition])
                }
            }
        }

}