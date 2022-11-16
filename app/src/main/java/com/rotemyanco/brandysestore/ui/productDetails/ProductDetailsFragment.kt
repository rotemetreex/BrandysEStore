package com.rotemyanco.brandysestore.ui.productDetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.rotemyanco.brandysestore.databinding.FragmentProductDetailsBinding
import com.rotemyanco.brandysestore.models.BaseProduct

class ProductDetailsFragment : Fragment() {

	private val logTag = "ProductDetailsFragment"

	private lateinit var productDetailsViewModel: ProductDetailsViewModel
	private lateinit var binding: FragmentProductDetailsBinding

	private lateinit var mBaseProduct: BaseProduct


	// annotation - required by getSerializable() --->          ??????
	@RequiresApi(Build.VERSION_CODES.TIRAMISU)
	override fun onAttach(context: Context) {
		super.onAttach(context)

		mBaseProduct = requireArguments().getSerializable("BASE_PRODUCT", BaseProduct::class.java)!!
		productDetailsViewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
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

		with(binding) {

			Log.d(logTag, "onViewCreated:        --->${mBaseProduct.productId}")

			tvProductDescFragDetails.text = mBaseProduct.productTitle
			tvProductCatFragDetails.text = mBaseProduct.firstLevelCategoryName

			tvProductDetailsUrlFragDetails.text = mBaseProduct.productDetailUrl

			tvProductPriceFragDetails.text = (mBaseProduct.appSalePrice).toString()
			tvCurrencyTypeFragDetails.text = mBaseProduct.appSalePriceCurrency

			val url = mBaseProduct.productMainImageUrl
			Glide
				.with(requireContext())
				.load(url)
				.fitCenter()
//                .placeholder(R.drawable.loading_spinner)
				.into(binding.ivMainImageUrlFragDetails)


			btnGoToAliExpressFragDetails.setOnClickListener {
				val i = Intent(Intent.ACTION_VIEW, Uri.parse(mBaseProduct.productDetailUrl))
				startActivity(i)
			}
		}
	}

}
