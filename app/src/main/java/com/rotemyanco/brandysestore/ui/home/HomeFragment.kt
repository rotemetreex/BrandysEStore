package com.rotemyanco.brandysestore.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rotemyanco.brandysestore.R
import com.rotemyanco.brandysestore.adapters.CategoryAdapter
import com.rotemyanco.brandysestore.adapters.PopularProductsAdapter
import com.rotemyanco.brandysestore.models.BaseProduct
import com.rotemyanco.brandysestore.models.Category
import com.rotemyanco.brandysestore.ui.categoryProducts.CategoryProductsFragment
import com.rotemyanco.brandysestore.ui.categoryProducts.CategoryProductsViewModel


class HomeFragment : Fragment() {

    private val logTag = "HomeFragment"

    private var bundle: Bundle = Bundle()
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var categoryProductsViewModel: CategoryProductsViewModel

    private lateinit var rcvCategories: RecyclerView
    private lateinit var rcvNewPopular: RecyclerView

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var popularProductsAdapter: PopularProductsAdapter
//    private lateinit var categoryProductsAdapter: CategoryProductsAdapter

    private var categoryList = mutableListOf<Category>()
    private var mPopularProductListSortedByNewest = mutableListOf<BaseProduct>()
//    private var mProductsByCat = mutableListOf<BaseProduct>()

    private lateinit var mCatId: String


    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        categoryProductsViewModel = ViewModelProvider(this)[CategoryProductsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =
            LayoutInflater.from(requireContext()).inflate(R.layout.fragment_home, container, false)

        rcvCategories = view.findViewById(R.id.rcv_categories_frag_home)
        rcvNewPopular = view.findViewById(R.id.rcv_newest_products_frag_home)


        categoryAdapter = CategoryAdapter(categoryList) { category ->
            Log.d(logTag, "onCreateView:            --------->          cat name: ${category.categoryName}")
            Log.d(logTag, "onCreateView:            --------->          cat ID: ${category.id}")

            mCatId = (category.id).toString()


            val catProductsFrag = CategoryProductsFragment.newInstance(mCatId)
            bundle = catProductsFrag.requireArguments()

            findNavController().navigate(
                R.id.action_navigation_home_to_categoryProductsFragment,
                bundle
            )
        }

        popularProductsAdapter = PopularProductsAdapter(mPopularProductListSortedByNewest)


        with(rcvCategories) {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(
                this@HomeFragment.requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )
        }

        with(rcvNewPopular) {
            adapter = popularProductsAdapter
            layoutManager = LinearLayoutManager(
                this@HomeFragment.requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )
        }

        homeViewModel.categories.observe(viewLifecycleOwner) { t ->
            t?.let {
                categoryList.addAll(it)
                categoryAdapter.notifyItemRangeChanged(0, categoryList.size)
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

        return view
    }


}


