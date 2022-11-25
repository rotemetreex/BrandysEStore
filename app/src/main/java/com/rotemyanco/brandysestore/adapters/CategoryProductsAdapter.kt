package com.rotemyanco.brandysestore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rotemyanco.brandysestore.databinding.ProductBlockBinding
import com.rotemyanco.brandysestore.models.BaseProduct


class CategoryProductsAdapter(
    private val productsByCat: List<BaseProduct>,
    private val onItemClicked: OnItemClick<BaseProduct>
):
    RecyclerView.Adapter<CategoryProductsAdapter.CategoryProductsVH>() {

    private val logTag = "CategoryProductsAdapter"
    private lateinit var baseProduct: BaseProduct
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductsVH {
        context = parent.context
        val binding =
            ProductBlockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryProductsVH(binding, productsByCat, onItemClicked)
    }

    override fun onBindViewHolder(holder: CategoryProductsVH, position: Int) {
            with(holder.binding) {
                baseProduct = productsByCat[position]
                tvNameProductBlock.text = baseProduct.productId
                val url = baseProduct.productMainImageUrl

                Glide
                    .with(context)
                    .load(url)
                    .fitCenter()
//                .placeholder(R.drawable.loading_spinner)
                    .into(ivImgProductBlock)
            }
    }

    override fun getItemCount(): Int = productsByCat.size

    class CategoryProductsVH(
        val binding: ProductBlockBinding,
        private val  productsByCat: List<BaseProduct>,
        private val onItemClicked: OnItemClick<BaseProduct>
        ) :
        RecyclerView.ViewHolder(binding.root)
    {
            init {
                binding.root.setOnClickListener {
                    onItemClicked.onItemClick(productsByCat[adapterPosition])
                }
            }
        }

}