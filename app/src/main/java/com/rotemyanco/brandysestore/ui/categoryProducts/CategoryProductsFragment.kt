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

	companion object {

		// Rename parameter arguments, choose names that match
		// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
		private const val ARG_PARAM1 = "CAT_ID"

		fun newInstance(param1: String): CategoryProductsFragment {
			val frag = CategoryProductsFragment()
			val args = Bundle()

			args.putString(ARG_PARAM1, param1)
			frag.arguments = args

			return frag
		}
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)

		bundle = requireArguments()
		mCatIdValue = requireArguments().getString(ARG_PARAM1).toString()

		catProductsViewModel = ViewModelProvider(this)[CategoryProductsViewModel::class.java]
		catProductsViewModel.setCatId(mCatIdValue!!)
	}


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCategoryProductsBinding.inflate(inflater, container, false)

		with(catProductsViewModel) {
			strCatId.observe(viewLifecycleOwner) {
				getAllProductsByCategoryId()
				productsByCat.observe(viewLifecycleOwner) { list ->
					val size = list?.size
					if (size != null) {
						when {
							size > 0 -> {
								list.let {
									mProductsByCat.addAll(list)
									// categoryProductsAdapter.notifyItemRangeChanged(mProductsByCat.size, 0)
									setUpAdapterAndRecycler()
								}
							}
							else -> {
								// list isnt populated yet!!
								// delay and wait for the response
								Log.d(logTag, "onCreateView:     else ->")
								Log.d(logTag, "make toast! - ")
								Toast.makeText(context, "*** NO DATA ***    --    no products in this category yet... ", Toast.LENGTH_LONG).show()

								findNavController().navigate(R.id.navigation_home)
							}
						}
					}

				}

			}
		}

		return binding.root
	}

	private fun setUpAdapterAndRecycler() {
		categoryProductsAdapter =
			CategoryProductsAdapter(mProductsByCat) { baseProduct ->
				mBaseProduct = baseProduct
				Log.d(logTag, "setUpAdapterAndRecycler:            ------  ${baseProduct.productId} -------")
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


//                    list?.let {
//                        mProductsByCat.addAll(list)
//                        setUpAdapterAndRecycler()
//
//                        categoryProductsAdapter.notifyItemRangeChanged(mProductsByCat.size, 0)
//                    }