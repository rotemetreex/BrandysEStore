package com.rotemyanco.brandysestore.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rotemyanco.brandysestore.adapters.CategoryAdapter
import com.rotemyanco.brandysestore.databinding.FragmentHomeBinding
import com.rotemyanco.brandysestore.models.Category


class HomeFragment : Fragment() {

    //    private val TAG = "HomeFragment"
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private var categoryList = mutableListOf<Category>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        categoryAdapter = CategoryAdapter(categoryList)

        with(binding.rcvCategoriesFragHome) {
            adapter = categoryAdapter
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
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


//        homeViewModel.categories.observe(viewLifecycleOwner, object : Observer<List<Category>> {
//            override fun onChanged(t: List<Category>?) {
//                t?.let {
//                    categoryList.addAll(it)
//                    categoryAdapter.notifyItemRangeChanged(0, categoryList.size)
//                }
//            }
//        }
//        )