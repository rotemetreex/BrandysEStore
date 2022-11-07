package com.rotemyanco.brandysestore.ui.categoryProducts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rotemyanco.brandysestore.R
import com.rotemyanco.brandysestore.adapters.CategoryProductsAdapter
import com.rotemyanco.brandysestore.databinding.FragmentCategoryProductsBinding
import com.rotemyanco.brandysestore.models.BaseProduct


class CategoryProductsFragment : Fragment() {

	private val logTag = "CategoryProductsFragment"
	private lateinit var bundle: Bundle

	private lateinit var catProductsViewModel: CategoryProductsViewModel
	private lateinit var binding: FragmentCategoryProductsBinding

	private lateinit var categoryProductsAdapter: CategoryProductsAdapter
	private var mProductsByCat = mutableListOf<BaseProduct>()

	private lateinit var mBaseProduct: BaseProduct

	//  Rename and change types of parameters
	private var mCatIdValue: String? = null


	override fun onAttach(context: Context) {
		super.onAttach(context)

		bundle = requireArguments()
		mCatIdValue = requireArguments().getString("CAT_ID").toString()

		catProductsViewModel = ViewModelProvider(this)[CategoryProductsViewModel::class.java]
		catProductsViewModel.setCatId(mCatIdValue!!)


		// check if i have correct data in strCatId in view-model -->
		// on attach logs
		with(catProductsViewModel) {
			Log.d(logTag, "onAttach:    ---> mCatIdValue = $mCatIdValue")
//			Log.d(logTag, "onAttach:    ---> strCatId.value = ${strCatId.value}")
//			Log.d(logTag, "onAttach:    ---> productsByCat.value?.size = ${productsByCat.value?.size}")
//			Log.d(logTag, "onAttach:    ---> productsByCat.value?.get(10) = ${productsByCat.value?.get(10)}")
		}
	}


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCategoryProductsBinding.inflate(inflater, container, false)

		with(catProductsViewModel) {
			val id = requireArguments().getString("CAT_ID").toString()
			Log.d(logTag, "onCreateView:      CAT_ID  ---> $id")
			getAllProductsByCategoryId(id)

			productsByCat.observe(viewLifecycleOwner) {
				it?.let {
					if (productsByCat.value?.isNotEmpty() == true) {
						mProductsByCat.addAll(it)
						Log.d(logTag, "onCreateView:     INSIDE OBSERVE  mProductsByCat.size -----> ${mProductsByCat.size}")
					} else {
						Toast.makeText(context, "*** NO DATA ***    --    no products in this category yet... ", Toast.LENGTH_LONG).show()
						findNavController().navigate(R.id.navigation_home)
					}
					categoryProductsAdapter = CategoryProductsAdapter(mProductsByCat) { baseProduct ->
						mBaseProduct = baseProduct
						Log.d(logTag, "onViewCreated:          baseProduct.id  ------  ${baseProduct.productId} -------")

						val b = Bundle()
						b.putString("PRODUCT_ID", baseProduct.productId)

						with(findNavController()) {
							if (currentDestination?.id == R.id.categoryProductsFragment) {
								navigate(R.id.action_categoryProductsFragment_to_productDetailsFragment, b)
							}
						}
					}
					with(binding.rcvCategoryProductsFragCategoryProducts) {
						adapter = categoryProductsAdapter
						layoutManager = GridLayoutManager(
							this@CategoryProductsFragment.requireContext(),
							2,
							GridLayoutManager.VERTICAL,
							false
						)
					}
				}
			}
			Log.d(logTag, "onCreateView:      OUTSIDE OBSERVE    mProductsByCat.size  ----->        ${mProductsByCat.size}")

		}
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

}





















//with(catProductsViewModel) {
//
////			strCatId.observe(viewLifecycleOwner) {
//				Log.d(logTag, "onCreateView:     ----->   BEFORE getAllProductsByCategoryId() ----   productsByCat.value.size = ${productsByCat.value?.size}")
//			requireArguments().getString("CAT_ID")?.let { getAllProductsByCategoryId(it) }
//				Log.d(logTag, "onCreateView:     ----->   AFTER getAllProductsByCategoryId() ----   productsByCat.value.size = ${productsByCat.value?.size}")
//
//				productsByCat.observe(viewLifecycleOwner) {
//					it?.let {
//						if (productsByCat.value?.isNotEmpty() == true) {
//							mProductsByCat.addAll(it)
//						} else {
//							Toast.makeText(context, "*** NO DATA ***    --    no products in this category yet... ", Toast.LENGTH_LONG).show()
//							findNavController().navigate(R.id.navigation_home)
//						}
//					}
//				}
//					categoryProductsAdapter = CategoryProductsAdapter(mProductsByCat) { baseProduct ->
//						mBaseProduct = baseProduct
//						Log.d(logTag, "onViewCreated:          baseProduct.id  ------  ${baseProduct.productId} -------")
//
//						val b = Bundle()
//						b.putString("PRODUCT_ID", baseProduct.productId)
//
////						findNavController().navigate(R.id.action_categoryProductsFragment_to_productDetailsFragment, b)
//
//						with(findNavController()) {
//							if (currentDestination?.id == R.id.categoryProductsFragment) {
//								navigate(R.id.action_categoryProductsFragment_to_productDetailsFragment, b)
//							}
//						}
//					}
//					with(binding.rcvCategoryProductsFragCategoryProducts) {
//						adapter = categoryProductsAdapter
//						layoutManager = GridLayoutManager(
//							this@CategoryProductsFragment.requireContext(),
//							2,
//							GridLayoutManager.VERTICAL,
//							false
//						)
//					}
////			}
//		}

