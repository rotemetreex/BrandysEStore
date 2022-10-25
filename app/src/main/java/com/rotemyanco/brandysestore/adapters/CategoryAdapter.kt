package com.rotemyanco.brandysestore.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rotemyanco.brandysestore.databinding.CategoryInfoCardBinding
import com.rotemyanco.brandysestore.models.Category


class CategoryAdapter(
    private val categories: List<Category>,
    var onItemClick: (Category) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    private val TAG = "CategoryAdapter"
    private lateinit var category: Category

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val viewBinding =
            CategoryInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryVH(viewBinding, categories, onItemClick)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        with(holder.viewBinding) {
            category = categories[position]
            tvSmallTitleCategoryInfoCard.text = category.categoryName
            Log.d(
                TAG,
                "onBindViewHolder:       categoryList[$position] --> ${category.categoryName}"
            )

        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryVH(
        val viewBinding: CategoryInfoCardBinding,
        private val categories: List<Category>,
        private val onItemClick: (Category) -> Unit,
        ) :
        RecyclerView.ViewHolder(viewBinding.root)
    {
            init {
                viewBinding.root.setOnClickListener {
                    onItemClick.invoke(categories[adapterPosition])
                }
            }
        }

}