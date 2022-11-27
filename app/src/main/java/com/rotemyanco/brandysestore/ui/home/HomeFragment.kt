package com.rotemyanco.brandysestore.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rotemyanco.brandysestore.R
import com.rotemyanco.brandysestore.adapters.CategoryAdapter
import com.rotemyanco.brandysestore.adapters.OnItemClick
import com.rotemyanco.brandysestore.adapters.PopularProductsAdapter
import com.rotemyanco.brandysestore.databinding.FragmentHomeBinding
import com.rotemyanco.brandysestore.models.BaseProduct
import com.rotemyanco.brandysestore.models.Category
import com.rotemyanco.brandysestore.ui.categoryProducts.CategoryProductsViewModel


class HomeFragment : Fragment() {

	private val logTag = "HomeFragment"
	private lateinit var binding: FragmentHomeBinding

	private lateinit var homeViewModel: HomeViewModel
	private lateinit var categoryProductsViewModel: CategoryProductsViewModel

	private lateinit var categoryAdapter: CategoryAdapter
	private lateinit var popularProductsAdapter: PopularProductsAdapter

	private var mCategoryList = mutableListOf<Category>()
	private var mmCategoryList = mutableListOf<Category>()
	private var mPopularProductListSortedByNewest = mutableListOf<BaseProduct>()

	private var mCatNameList = mutableListOf<String>()
	private lateinit var actvSearch: AutoCompleteTextView


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
		categoryProductsViewModel = ViewModelProvider(this)[CategoryProductsViewModel::class.java]
		binding = FragmentHomeBinding.inflate(inflater, container, false)
		actvSearch = binding.actvSearchFragHome

		categoryAdapter = CategoryAdapter(mCategoryList, onItemClicked = object : OnItemClick<Category> {
			override fun onItemClick(item: Category) {

				val bundle = Bundle()
				bundle.putString("CAT_ID", (item.id).toString())
				bundle.putString("CAT_NAME", item.categoryName)

				findNavController(this@HomeFragment).navigate(
					R.id.action_navigation_home_to_categoryProductsFragment, bundle
				)
			}
		}, onLastBtnClick = object : OnItemClick<Int> {
			override fun onItemClick(item: Int) {
				when (item) {
					R.id.btn_browse_rcv_last_btn -> {
						binding.actvSearchFragHome.visibility = View.VISIBLE
						binding.actvSearchFragHome.requestFocus()
						showKeyBoard(true)
					}
					else -> View.GONE
				}
			}
		})

		popularProductsAdapter = PopularProductsAdapter(mPopularProductListSortedByNewest, onItemClicked = object : OnItemClick<BaseProduct> {
			override fun onItemClick(item: BaseProduct) {
				val bundle = Bundle()
				bundle.putSerializable("BASE_PRODUCT", item)

				findNavController(this@HomeFragment).navigate(
					R.id.action_navigation_home_to_productDetailsFragment, bundle
				)
			}

		})

		with(binding) {
			with(rcvCategoriesFragHome) {
				adapter = categoryAdapter
				layoutManager = LinearLayoutManager(
					this@HomeFragment.requireContext(), RecyclerView.HORIZONTAL, false
				)
			}
			with(rcvNewestProductsFragHome) {
				adapter = popularProductsAdapter
				layoutManager = LinearLayoutManager(
					this@HomeFragment.requireContext(), RecyclerView.HORIZONTAL, false
				)
			}
		}
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(homeViewModel) {

			popularCategories.observe(viewLifecycleOwner) { t ->
				t?.let {
					if (mCategoryList.size == 0) {
						mCategoryList.addAll(it)
						categoryAdapter.notifyItemRangeChanged(0, mCategoryList.size)
					}
				}
			}

			unfilteredCategories.observe(viewLifecycleOwner) { t ->
				t?.let {
					if (mmCategoryList.size == 0) {
						mmCategoryList.addAll(it)
						categoryAdapter.notifyItemRangeChanged(0, mmCategoryList.size)
					}

					val dropDownItemsResArray = mmCategoryList.toTypedArray()
					val mAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, dropDownItemsResArray)
					actvSearch.threshold = 1
					actvSearch.setAdapter(mAdapter)

					with(actvSearch) {

						setOnItemClickListener { parent, view, position, id ->
							showKeyBoard(false)
							actvSearch.text = null
							actvSearch.isFocusable = false

							val selectedObj = parent.getItemAtPosition(position) as Category

							val bundle = Bundle()
							bundle.putString("CAT_ID", (selectedObj.id).toString())
							bundle.putString("CAT_NAME", selectedObj.categoryName)

							findNavController(this@HomeFragment).navigate(
								R.id.action_navigation_home_to_categoryProductsFragment, bundle
							)
						}
					}
				}
			}
			catNameList.observe(viewLifecycleOwner) { t ->
				t?.let {
					if (mCatNameList.size == 0) {
						mCatNameList.addAll(it)
					}
				}
			}
			productsSortByPopularNewArrivals.observe(viewLifecycleOwner) { t ->
				t?.let {
					if (mPopularProductListSortedByNewest.size == 0) {
						mPopularProductListSortedByNewest.addAll(it)
						popularProductsAdapter.notifyItemRangeChanged(
							0, mPopularProductListSortedByNewest.size
						)
					}
				}
			}
		}
	}


	private fun showKeyBoard(status: Boolean) {
		val view = activity?.currentFocus
		if (view != null) {
			val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
			if (status) {
				imm.showSoftInput(view, 0)
			} else {
				imm.hideSoftInputFromWindow(view.windowToken, 0)
			}
		}
	}

}
