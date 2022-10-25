package com.rotemyanco.brandysestore.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rotemyanco.brandysestore.App
import com.rotemyanco.brandysestore.models.BaseProduct
import com.rotemyanco.brandysestore.models.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val logTag = "HomeViewModel"

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()

    val categories: LiveData<List<Category>>
        get() = _categories


    private val _productsSortByPopularNewArrivals: MutableLiveData<List<BaseProduct>> =
        MutableLiveData<List<BaseProduct>>()

    val productsSortByPopularNewArrivals: LiveData<List<BaseProduct>>
        get() = _productsSortByPopularNewArrivals

    init {
        Log.d(logTag, "    --------> init:     BEFORE launch")
        viewModelScope.launch(Dispatchers.IO) {

            val categories = App.repo.getAllProductCategories()
            _categories.postValue(categories)
            Log.d(logTag, "    --------> init:     DURING launch")
            val popularNewProducts = App.repo.getProductListSortedByPopularNewArrival()
            _productsSortByPopularNewArrivals.postValue(popularNewProducts)

        }
        Log.d(logTag, "    --------> init:     AFTER launch")
    }

}