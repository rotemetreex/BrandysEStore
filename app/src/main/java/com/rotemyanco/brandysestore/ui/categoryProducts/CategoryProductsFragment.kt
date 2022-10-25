package com.rotemyanco.brandysestore.ui.categoryProducts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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
        Log.d(logTag, "onAttach:     requireArguments().getString(ARG_PARAM1).toString() = ${ requireArguments().getString(ARG_PARAM1).toString() }")
        Log.d(logTag, "onAttach:    catProductsViewModel.strCatId.value = ${catProductsViewModel.strCatId.value}")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryProductsBinding.inflate(inflater, container, false)

        with(catProductsViewModel) {
            strCatId.observe(viewLifecycleOwner) { stringCatId ->
                Log.d(logTag, "   ----> onCreateView  --->     inside observe         ----------> stringCatId = $stringCatId")
                Log.d(logTag, "   ----> onCreateView  --->     inside observe         ----------> catProductsViewModel.strCatId.value = ${catProductsViewModel.strCatId.value}")

                getAllProductsByCategoryId()
                productsByCat.observe(viewLifecycleOwner) { list ->
                    val size = list?.size
                    if (size != null) {
                        when {
                            size > 0 -> {
                                list.let {
                                    mProductsByCat.addAll(list)
                                    Log.d(logTag,"----> onCreateView --->     inside observe of *** strCatId *** :    mProductsByCat.size = ${mProductsByCat.size}  ------------")

                                    categoryProductsAdapter = CategoryProductsAdapter(mProductsByCat) { baseProduct ->
                                        mBaseProduct = baseProduct
                                        Log.d(logTag, "onCreateView:            ------  $baseProduct -------")
                                    }

                                    with(binding.rcvCategoryProductsFragCategoryProducts) {
                                        adapter = categoryProductsAdapter
                                        layoutManager = GridLayoutManager(
                                            this@CategoryProductsFragment.requireContext(),
                                            3,
                                            GridLayoutManager.VERTICAL,
                                            false
                                        )
                                        Log.d(logTag, "onCreateView:     ---> inside rcv AFTER setup is done")
                                    }

                                    //                        categoryProductsAdapter.notifyItemRangeChanged(mProductsByCat.size, 0)
                                }
                            }
                            else -> {

                                // list isnt populated yet!!
                                // delay and wait for the response
                                Log.d(logTag, "onCreateView:     else ->")
                            }
                        }
                    }

                }

            }
            Log.d(logTag, "onCreateView:        -----> ${productsByCat.value}")
        }

//        when {
//            mProductsByCat.size > 0 -> {
//                categoryProductsAdapter = CategoryProductsAdapter(mProductsByCat) { baseProduct ->
//                    mBaseProduct = baseProduct
//                    Log.d(logTag, "onCreateView:            ------  $baseProduct -------")
//                }
//
//                with(binding.rcvCategoryProductsFragCategoryProducts) {
//                    adapter = categoryProductsAdapter
//                    layoutManager = GridLayoutManager(
//                        this@CategoryProductsFragment.requireContext(),
//                        3,
//                        GridLayoutManager.VERTICAL,
//                        false
//                    )
//                    Log.d(logTag, "onCreateView:     ---> inside rcv AFTER setup is done")
//                }
//            }
//            else -> {
//                // delay 1 sec and than repeat case 1
//                println("**** delay is needed ***")
//            }
//        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val x = catProductsViewModel.strCatId.value
        Log.d(logTag, "onViewCreated:             ---------> mCatIdValue:   $mCatIdValue")
        Log.d(logTag, "onViewCreated:             ---------> strCatId.value:   $x")




    }


}


//        // observe the now set cat id
//        viewModel.strCatId.observe(viewLifecycleOwner) {
//            when {
//                (it != mCatIdValue) -> {
//                    // set fun from viewmodel to set catId (mutable)
//                    // to what ever we got in the bundle args --> mCatId
//                    mCatIdValue?.let {
//                        println("----- $mCatIdValue ----")
//                        with(viewModel) {
//                            setCatId(mCatIdValue!!)
//                        }
//                    }
//                    categoryProductsAdapter.notifyItemChanged(mCatIdValue!!.toInt())
//                }
//                else -> {
//                    println("----------- mCatIdValue: $mCatIdValue   ;    viewModel.strCatId.observe -> it: $it-------------")
//                }
//            }
//        }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        mCatIdValue = requireArguments().getString(ARG_PARAM1)
//        println("----------------- on view created:      from BUNDLE ARGUMENTS:            mCatIdValue: $mCatIdValue ------------------")
//        args = requireArguments()
//        println("----------------- on view created:      from BUNDLE ARGUMENTS:            mCatIdValue: $args ------------------")
//    }


//mCatIdValue?.let {
//            with(viewModel) {
//                setCatId(it)
//
//                strCatId.observe(viewLifecycleOwner) { t ->
//                    println(t)
//                    categoryProductsAdapter.notifyItemChanged(mCatIdValue!!.toInt())
//                }
//            }
//        }

//        mCatIdValue = requireArguments().getString(ARG_PARAM1)
//        println("----------------- on create view:      from BUNDLE ARGUMENTS:            mCatIdValue: $mCatIdValue ------------------")
//        args = requireArguments()








//            strCatId.observe(viewLifecycleOwner) {
//                Log.d(logTag, "   ----> onViewCreated --->     inside observe         ----------> strCatId.value = ${strCatId.value}")
//
//                val catIdString = it.toInt()
//                categoryProductsAdapter.notifyItemChanged(catIdString)
//
//            }





//            productsByCat.observe(viewLifecycleOwner) {
//                it?.let {
//                    mProductsByCat.addAll(it)
//                    Log.d(logTag,"----> onViewCreated --->     inside observe:       mProductsByCat.size = ${mProductsByCat.size}  ------------")
//                    categoryProductsAdapter.notifyItemRangeChanged(0, mProductsByCat.size)
//                }
//            }



//        val x = catProductsViewModel.strCatId.value
//        Log.d(logTag, "onCreateView:             ---------> mCatIdValue:   $mCatIdValue")
//        Log.d(logTag, "onCreateView:             ---------> strCatId.value:   $x")

//        categoryProductsAdapter = CategoryProductsAdapter(mProductsByCat) { baseProduct ->
//            mBaseProduct = baseProduct
//            Log.d(logTag, "onCreateView:            ------  $baseProduct -------")
//        }
//
//        with(binding.rcvCategoryProductsFragCategoryProducts) {
//            adapter = categoryProductsAdapter
//            layoutManager = GridLayoutManager(
//                this@CategoryProductsFragment.requireContext(),
//                3,
//                GridLayoutManager.VERTICAL,
//                false
//            )
//        }