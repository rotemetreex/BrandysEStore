package com.rotemyanco.brandysestore.ui.productDetails

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rotemyanco.brandysestore.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment() {

    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var binding: FragmentProductDetailsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        viewModel.popularProduct.observe(viewLifecycleOwner) {t ->
//            t?.let {
//                println(" ----------------    it: PopularProduct ---> ${it.popularProductId}")
//                println(" ----------------    it: PopularProduct ---> ${it.productTitle}")
//                println(" ----------------    it: PopularProduct ---> ${it.productDetailUrl}")
//                println(" ----------------    it: PopularProduct ---> ${it.productId}")
//                println(" ----------------    it: PopularProduct ---> ${it.productMainImageUrl}")
//
//                binding.tvProductNameFragDetails.text = it.productId
//                binding.tvProductDetailsUrlFragDetails.text = it.productDetailUrl
//                binding.tvIdFragDetails.text = it.popularProductId
//
//                val url = it.productMainImageUrl
//                Glide
//                    .with(requireContext())
//                    .load(url)
//                    .fitCenter()
////                .placeholder(R.drawable.loading_spinner)
//                    .into(binding.ivMainImageUrlFragDetails)
//            }
//        }

    }




















}