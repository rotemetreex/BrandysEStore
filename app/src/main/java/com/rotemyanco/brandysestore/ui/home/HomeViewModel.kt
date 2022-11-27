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
import kotlinx.coroutines.*


class HomeViewModel(application: Application) : AndroidViewModel(application) {

	private val logTag = "HomeViewModel"


	private val _unfilteredCategories: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()

	val unfilteredCategories: LiveData<List<Category>>
		get() = _unfilteredCategories


	private val _popularCategories: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()

	val popularCategories: LiveData<List<Category>>
		get() = _popularCategories


	private val _productsSortByPopularNewArrivals: MutableLiveData<List<BaseProduct>> =
		MutableLiveData<List<BaseProduct>>()

	val productsSortByPopularNewArrivals: LiveData<List<BaseProduct>>
		get() = _productsSortByPopularNewArrivals

	private val _catNameList: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
	val catNameList: LiveData<List<String>>
		get() = _catNameList


	init {
		Log.d(logTag, "    --------> init:     BEFORE launch")
		viewModelScope.launch {

			val tempList = mutableListOf<String>()
			val unfilteredCatList = App.repo.getAllProductCategories()
			_unfilteredCategories.postValue(unfilteredCatList)

			for (cat in unfilteredCatList) {
				tempList.add(cat.categoryName)
			}
			_catNameList.postValue(tempList)

			val stringIdTempList = listOf(
				"701",
				"702",
				"703",
				"509",
				"390501",
				"100005063",
				"100005085",
				"100005329",
				"100003155",
				"200000285",
				"200001076",
				"200001077",
				"200001086",
				"200001085",
				"200001083",
				"200001081",
				"200001388",
				"200001598",
				"200002396"
			)

			val popularCategories = mutableListOf<Category>()
			for (catId in stringIdTempList) {
				for (cat in unfilteredCatList) {
					if (!popularCategories.contains(cat) && catId.toInt() == cat.id) {
						popularCategories.add(cat)
					}
				}
			}
			Log.d(logTag, "INSIDE init     ---->             popularCategories.size:      ${popularCategories.size}")
			Log.d(logTag, "INSIDE init     ---->             _popularCategories.value?.size:      ${_popularCategories.value?.size}")
			_popularCategories.postValue(popularCategories)

			val popularNewProducts = App.repo.getProductListSortedByPopularNewArrival()
			_productsSortByPopularNewArrivals.postValue(popularNewProducts)
		}
		Log.d(logTag, "home view model init:              AFTER init -------->    _catNameList.value?.size:   ${_catNameList.value?.size}")
		Log.d(logTag, "home view model init:              AFTER init -------->    _popularCategories.value?.size:   ${_popularCategories.value?.size}")
	}

}
