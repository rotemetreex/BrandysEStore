package com.rotemyanco.brandysestore.ui.categoryProducts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rotemyanco.brandysestore.R
import com.rotemyanco.brandysestore.adapters.CategoryProductsAdapter
import com.rotemyanco.brandysestore.adapters.OnItemClick
import com.rotemyanco.brandysestore.databinding.FragmentCategoryProductsBinding
import com.rotemyanco.brandysestore.models.BaseProduct


class CategoryProductsFragment : Fragment() {

	private val logTag = "CategoryProductsFragment"

	private lateinit var catProductsViewModel: CategoryProductsViewModel
	private lateinit var binding: FragmentCategoryProductsBinding

	private lateinit var categoryProductsAdapter: CategoryProductsAdapter
	private var mProductsByCat = mutableListOf<BaseProduct>()

	private lateinit var mBaseProduct: BaseProduct

	//  Rename and change types of parameters
	private var mCatIdValue: String? = null


	override fun onAttach(context: Context) {
		super.onAttach(context)

		mCatIdValue = requireArguments().getString("CAT_ID").toString()

		catProductsViewModel = ViewModelProvider(this)[CategoryProductsViewModel::class.java]

		// check if i have correct data in strCatId in view-model -->
		// on attach logs
		Log.d(logTag, "onAttach:    ---> mCatIdValue = $mCatIdValue")
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCategoryProductsBinding.inflate(inflater, container, false)

		with(catProductsViewModel) {
			val name = requireArguments().getString("CAT_NAME").toString()
			binding.tvCatTitleFragCategoryProducts.text = name

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

					categoryProductsAdapter = CategoryProductsAdapter(mProductsByCat,
						onItemClicked = object : OnItemClick<BaseProduct> {
							override fun onItemClick(item: BaseProduct) {
								mBaseProduct = item
								Log.d(logTag, "onViewCreated:          baseProduct.id  ------  ${item.productId} -------")

								val bundle = Bundle()
								bundle.putString("PRODUCT_ID", item.productId)
								bundle.putSerializable("BASE_PRODUCT", mBaseProduct)

								findNavController(this@CategoryProductsFragment).navigate(
									R.id.action_categoryProductsFragment_to_productDetailsFragment,
									bundle
								)
							}
						})

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


}