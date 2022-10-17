package com.rotemyanco.brandysestore.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rotemyanco.brandysestore.databinding.CategoryInfoCardBinding
import com.rotemyanco.brandysestore.models.Category




class CategoryAdapter(private val categories: List<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    private val TAG = "CategoryAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val viewBinding = CategoryInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryVH(viewBinding)
    }


    override fun onBindViewHolder(holder: CategoryVH, position: Int) {

        with(holder.viewBinding) {
            val category = categories[position]
            println(category)

            tvSmallTitleCategoryInfoCard.text = category.categoryName

            Log.d(TAG, "onBindViewHolder:       categoryList[$position] --> ${category.categoryName}")
//            ivSmallImageInfoCard.setImageDrawable()
            }
        }


    override fun getItemCount(): Int = categories.size



    class CategoryVH(val viewBinding: CategoryInfoCardBinding): RecyclerView.ViewHolder(viewBinding.root)

    }







