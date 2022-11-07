package com.rotemyanco.brandysestore.ui.productDetails

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rotemyanco.brandysestore.databinding.FragmentProductDetailsBinding
import com.rotemyanco.brandysestore.models.BaseProduct

class ProductDetailsFragment : Fragment() {

	private val logTag = "ProductDetailsFragment"
	private lateinit var bundle: Bundle

	private lateinit var productDetailsViewModel: ProductDetailsViewModel
	private lateinit var binding: FragmentProductDetailsBinding

	private lateinit var mBaseProduct: BaseProduct

	//  Rename and change types of parameters
	private var mProductIdValue: String? = null


	override fun onAttach(context: Context) {
		super.onAttach(context)

		bundle = requireArguments()
		mProductIdValue = requireArguments().getString("PRODUCT_ID").toString()

		productDetailsViewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
		productDetailsViewModel.setBaseProductId(mProductIdValue!!)
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


		productDetailsViewModel.baseProduct.observe(viewLifecycleOwner) {
			mBaseProduct = it
			Log.d(logTag, "onViewCreated:       inside baseProduct observe() --->${it.productId}")
			Log.d(logTag, "onViewCreated:       inside baseProduct observe() --->${mBaseProduct.productId}")

			with(binding) {


				tvProductNameFragDetails.text = mBaseProduct.productId
				tvProductDetailsUrlFragDetails.text = mBaseProduct.productDetailUrl
				tvIdFragDetails.text = mBaseProduct.shopName

				val url = mBaseProduct.productMainImageUrl
				Glide
					.with(requireContext())
					.load(url)
					.fitCenter()
//                .placeholder(R.drawable.loading_spinner)
					.into(binding.ivMainImageUrlFragDetails)
			}
		}
	}


}
