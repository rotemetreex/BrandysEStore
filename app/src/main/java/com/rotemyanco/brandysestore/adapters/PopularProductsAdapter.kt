package com.rotemyanco.brandysestore.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rotemyanco.brandysestore.databinding.PopularInfoCardBinding
import com.rotemyanco.brandysestore.models.BaseProduct


class PopularProductsAdapter(private val popularProductList: List<BaseProduct>):
    RecyclerView.Adapter<PopularProductsAdapter.PopularProductsVH>() {

    private lateinit var context: Context
    val tag = "PopularProductsAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularProductsVH {
        context = parent.context
        val binding = PopularInfoCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return PopularProductsVH(binding)
    }

    override fun onBindViewHolder(holder: PopularProductsVH, position: Int) {
        with(holder.binding) {
            Log.d(tag, "onBindViewHolder:   ------------- ${popularProductList[position]}")
            val popularProduct = popularProductList[position]
            Log.d(tag, "onBindViewHolder:   ------------- ${popularProduct.productMainImageUrl}")
            Log.d(tag, "onBindViewHolder:   ------------- ${popularProduct.appSalePrice}")
            println(popularProduct)

            val appPriceDouble = "${popularProduct.appSalePrice}"
            tvSmallTitlePopularInfoCard.text = appPriceDouble
            val url = popularProduct.productMainImageUrl

            Glide
                .with(context)
                .load(url)
                .fitCenter()
//                .placeholder(R.drawable.loading_spinner)
                .into(ivSmallImagePopularInfoCard)
        }
    }

    override fun getItemCount(): Int = popularProductList.size


    class PopularProductsVH(val binding: PopularInfoCardBinding): RecyclerView.ViewHolder(binding.root)
}