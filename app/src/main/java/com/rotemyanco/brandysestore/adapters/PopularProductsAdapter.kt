package com.rotemyanco.brandysestore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rotemyanco.brandysestore.databinding.PopularInfoCardBinding
import com.rotemyanco.brandysestore.models.Product




class PopularProductsAdapter(private val popularProducts: List<Product>):
    RecyclerView.Adapter<PopularProductsAdapter.PopularProductsVH>() {

    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularProductsVH {
        context = parent.context
        val binding = PopularInfoCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return PopularProductsVH(binding)
    }

    override fun onBindViewHolder(holder: PopularProductsVH, position: Int) {
        with(holder.binding) {
            val popularProduct = popularProducts[position]
            println(popularProduct)

            tvSmallTitlePopularInfoCard.text = popularProduct.productId
            val url = popularProduct.productMainImageUrl

            Glide
                .with(context)
                .load(url)
                .fitCenter()
//                .placeholder(R.drawable.loading_spinner)
                .into(ivSmallImagePopularInfoCard)
        }
    }

    override fun getItemCount(): Int = popularProducts.size


    class PopularProductsVH(val binding: PopularInfoCardBinding): RecyclerView.ViewHolder(binding.root)
}