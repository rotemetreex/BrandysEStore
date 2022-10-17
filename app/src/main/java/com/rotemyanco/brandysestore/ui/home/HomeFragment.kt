package com.rotemyanco.brandysestore.ui.home

import android.content.Context
import android.os.Bundle
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
import com.rotemyanco.brandysestore.models.Category
import com.rotemyanco.brandysestore.models.Product


class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
//    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var rcvCategories: RecyclerView
    private lateinit var rcvNewPopular: RecyclerView

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var popularProductsAdapter: PopularProductsAdapter

    private var categoryList = mutableListOf<Category>()
    private var newAndPopularProductList = mutableListOf<Product>()

    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_home, container, false)
//        val v = inflater.inflate(R.layout.fragment_home, container, false)
        rcvCategories = view.findViewById(R.id.rcv_categories_frag_home)
        rcvNewPopular = view.findViewById(R.id.rcv_newest_products_frag_home)
//         = FragmentHomeBinding.inflate(inflater, container, false)

        categoryAdapter = CategoryAdapter(categoryList)
        popularProductsAdapter = PopularProductsAdapter(newAndPopularProductList)

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
                newAndPopularProductList.addAll(it)
                popularProductsAdapter.notifyItemRangeChanged(0, newAndPopularProductList.size)
            }
        }


        return view
    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}


