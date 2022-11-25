package com.rotemyanco.brandysestore.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
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

//	private val mLayoutManager: LinearLayoutManager = LinearLayoutManager(
//		this@HomeFragment.requireContext(),
//		RecyclerView.HORIZONTAL,
//		false
//	)

	private lateinit var categoryAdapter: CategoryAdapter
	private lateinit var popularProductsAdapter: PopularProductsAdapter

	private var mCategoryList = mutableListOf<Category>()
	private var mmCategoryList = mutableListOf<Category>()
	private var mPopularProductListSortedByNewest = mutableListOf<BaseProduct>()

	private var mCatNameList = mutableListOf<String>()

	private lateinit var mCatId: String
	private lateinit var actvSearch: AutoCompleteTextView
	private lateinit var imm: InputMethodManager


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
		categoryProductsViewModel = ViewModelProvider(this)[CategoryProductsViewModel::class.java]
		binding = FragmentHomeBinding.inflate(inflater, container, false)
		actvSearch = binding.actvSearchFragHome

		imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


		categoryAdapter = CategoryAdapter(mCategoryList,
			onItemClicked = object : OnItemClick<Category> {
				override fun onItemClick(item: Category) {

					mCatId = (item.id).toString()

					val bundle = Bundle()
					bundle.putString("CAT_ID", mCatId)

					findNavController(this@HomeFragment).navigate(
						R.id.action_navigation_home_to_categoryProductsFragment,
						bundle
					)
				}
			},
			onLastBtnClick = object : OnItemClick<Int> {
				override fun onItemClick(item: Int) {
					binding.actvSearchFragHome.visibility =
						if (item == R.id.btn_browse_rcv_last_btn) {
							View.VISIBLE
						} else View.GONE
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

		with(homeViewModel) {

			popularCategories.observe(viewLifecycleOwner) { t ->
				t?.let {
					mCategoryList.addAll(it)
					categoryAdapter.notifyItemRangeChanged(0, mCategoryList.size)
				}
			}

			unfilteredCategories.observe(viewLifecycleOwner) { t ->
				t?.let {
					mmCategoryList.addAll(it)
					val dropDownItemsResArray = mmCategoryList.toTypedArray()
					val mAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, dropDownItemsResArray)
					actvSearch.threshold = 1
					actvSearch.setAdapter(mAdapter)

					with(actvSearch) {

						setOnFocusChangeListener { view, e ->
							actvSearch.requestFocus()

//							(actvSearch as EditText).setText("")
							actvSearch.performCompletion()
							(actvSearch as TextView).text = ""
							actvSearch.performClick()
						}

						setOnTouchListener(View.OnTouchListener { v, e ->
							if (e.action == KeyEvent.ACTION_DOWN) {

								v.performClick()
								actvSearch.setSelection(actvSearch.text.length)
							}
							return@OnTouchListener true
						})
						setOnItemClickListener { parent, view, position, id ->

							actvSearch.text = null
							actvSearch.isFocusable = false

							val selectedItem = parent.getItemAtPosition(position).toString()
							val selectedItemId = (parent.getItemAtPosition(position) as Category).id
							Log.d(logTag, "onCreateView:          ----> $selectedItem   ")
							Log.d(logTag, "onCreateView:          ----> $selectedItemId   ")

							val bundle = Bundle()
							bundle.putString("CAT_ID", selectedItemId.toString())

							findNavController(this@HomeFragment).navigate(
								R.id.action_navigation_home_to_categoryProductsFragment,
								bundle
							)
						}
						setOnDismissListener {
							Log.d(logTag, "onCreateView:          ----> Suggestion is Closed")
							imm.hideSoftInputFromWindow(actvSearch.windowToken, 0)

						}
					}
				}
			}
			catNameList.observe(viewLifecycleOwner) { t ->
				t?.let {
					mCatNameList.addAll(it)
				}
			}
			productsSortByPopularNewArrivals.observe(viewLifecycleOwner) { t ->
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

}


// To animate view slide out from bottom to top
//	private fun slideToTop(view: View) {
//		view.visibility = View.VISIBLE
//		val animate = TranslateAnimation(0F, 0F, 0F, (-view.height).toFloat())
//		animate.duration = 500
//		animate.fillAfter = true
//		view.startAnimation(animate)
//		view.visibility = View.GONE
//	}


//			val et = actvSearch as EditText
//				with(et) {
//					when (EditText::class.java) {
//						this -> {
//							setSelection(0)
//						}
//					}
//			}
