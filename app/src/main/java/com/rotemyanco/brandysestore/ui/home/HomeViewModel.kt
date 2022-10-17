package com.rotemyanco.brandysestore.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rotemyanco.brandysestore.models.Category
import com.rotemyanco.brandysestore.models.Product
import com.rotemyanco.brandysestore.retrofit.MagicAliExpressAPIRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _categories: MutableLiveData<List<Category>> =
        MutableLiveData<List<Category>>().apply {

        }

    val categories: LiveData<List<Category>>
        get() = _categories


    private val _productsSortByPopularNewArrivals: MutableLiveData<List<Product>> =
        MutableLiveData<List<Product>>().apply {

        }

    val productsSortByPopularNewArrivals: LiveData<List<Product>>
        get() = _productsSortByPopularNewArrivals


    init {

        val repo = MagicAliExpressAPIRepo(application)

        viewModelScope.launch(Dispatchers.IO) {

            val categories = repo.getAllProductCategories()
            _categories.postValue(categories)

            val popularNewProducts = repo.getProductListSortedByPopularNewArrival()
            _productsSortByPopularNewArrivals.postValue(popularNewProducts)
        }

    }

}