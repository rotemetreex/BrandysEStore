package com.rotemyanco.brandysestore.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rotemyanco.brandysestore.R
import com.rotemyanco.brandysestore.adapters.CategoryAdapter
import com.rotemyanco.brandysestore.adapters.PopularProductsAdapter
import com.rotemyanco.brandysestore.databinding.FragmentHomeBinding
import com.rotemyanco.brandysestore.models.BaseProduct
import com.rotemyanco.brandysestore.models.Category
import com.rotemyanco.brandysestore.ui.categoryProducts.CategoryProductsViewModel
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.rotemyanco.brandysestore.adapters.OnItemClick

class HomeFragment : Fragment() {

	private val logTag = "HomeFragment"
	private lateinit var binding: FragmentHomeBinding

	private lateinit var homeViewModel: HomeViewModel
	private lateinit var categoryProductsViewModel: CategoryProductsViewModel

//	private val mLayoutManager: LinearLayoutManager = LinearLayoutManager(
//		this@HomeFragment.requireContext(),
//		RecyclerView.HORIZONTAL,
//		false
//	)

	private lateinit var categoryAdapter: CategoryAdapter
	private lateinit var popularProductsAdapter: PopularProductsAdapter

	private var mCategoryList = mutableListOf<Category>()
	private var mPopularProductListSortedByNewest = mutableListOf<BaseProduct>()

	private lateinit var mCatId: String


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
		categoryProductsViewModel = ViewModelProvider(this)[CategoryProductsViewModel::class.java]
		binding = FragmentHomeBinding.inflate(inflater, container, false)


		categoryAdapter = CategoryAdapter(mCategoryList,
			onItemClicked = object : OnItemClick<Category> {
				override fun onItemClick(item: Category) {
					Log.d(logTag, "onCreateView:            --------->          cat name: ${item.categoryName}")
					Log.d(logTag, "onCreateView:            --------->          cat ID: ${item.id}")

					mCatId = (item.id).toString()

					val bundle = Bundle()
					bundle.putString("CAT_ID", mCatId)

					findNavController(this@HomeFragment).navigate(
						R.id.action_navigation_home_to_categoryProductsFragment,
						bundle
					)
				}
			})

		popularProductsAdapter = PopularProductsAdapter(mPopularProductListSortedByNewest)

		with(binding) {
			with(rcvCategoriesFragHome) {
				adapter = categoryAdapter
				layoutManager = LinearLayoutManager(
					this@HomeFragment.requireContext(),
					RecyclerView.HORIZONTAL,
					false
				)
			}
			with(rcvNewestProductsFragHome) {
				adapter = popularProductsAdapter
				layoutManager = LinearLayoutManager(
					this@HomeFragment.requireContext(),
					RecyclerView.HORIZONTAL,
					false
				)
			}
		}
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		homeViewModel.popularCategories.observe(viewLifecycleOwner) { t ->
			t?.let {
				mCategoryList.addAll(it)
				categoryAdapter.notifyItemRangeChanged(0, mCategoryList.size)
			}
		}

		homeViewModel.productsSortByPopularNewArrivals.observe(viewLifecycleOwner) { t ->
			t?.let {
				mPopularProductListSortedByNewest.addAll(it)
				popularProductsAdapter.notifyItemRangeChanged(
					0,
					mPopularProductListSortedByNewest.size
				)
			}
		}
	}
}
