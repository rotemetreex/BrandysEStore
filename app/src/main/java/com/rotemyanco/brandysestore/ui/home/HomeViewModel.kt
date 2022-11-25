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

			val stringIdTempList = mutableListOf(
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
					if (catId.toInt() == cat.id) {
						popularCategories.add(cat)
					}
				}
				_popularCategories.postValue(popularCategories)
			}
			val popularNewProducts = App.repo.getProductListSortedByPopularNewArrival()
			_productsSortByPopularNewArrivals.postValue(popularNewProducts)
		}
		Log.d(logTag, "home view model init:              AFTER init -------->    _catNameList.value?.size:   ${_catNameList.value?.size}")
	}

}


//init {
//    Log.d(logTag, "    --------> init:     BEFORE launch")
//    viewModelScope.launch(Dispatchers.IO) {
//
//        val categories = App.repo.getAllProductCategories()
//        _categories.postValue(categories)
//
//
//        Log.d(logTag, "    --------> init:     DURING launch")
//
//        val popularNewProducts = App.repo.getProductListSortedByPopularNewArrival()
//        _productsSortByPopularNewArrivals.postValue(popularNewProducts)
//
//    }
//    Log.d(logTag, "    --------> init:     AFTER launch")
//}


//			val tempList = mutableListOf<String>()
//
//			for (cat in categories) {
//				delay(1800L)
//				Log.d(logTag, "inside for loop -----> after 1800L ml sec delay")
//				val productList = App.repo.getAllProductsByCategoryId((cat.id).toString()).docs

//				if (productList.isEmpty()) {
//					Log.d(logTag, "InValid (!!!!) cat ID:               ** ${cat.id} **")
//					tempList.add((cat.id).toString())
//				}

//				if (productList.isNotEmpty()) {
//					Log.d(logTag, "valid cat ID:               ** ${cat.id} **")
//
//					tempList.add((cat.id).toString())
//					println("-------  ****  temp list ****  ------ ")
//					for (id in tempList) {
//						println("-------  ****  valid id:    $id  ****  ------ ")
//					}
//				}
//		}

//			_validCatIdList.postValue(tempList)


//	private fun getCatDropdownStrList(): List<String> {
//		val nameList = mutableListOf<String>()
//		if (!_unfilteredCategories.value.isNullOrEmpty()) {
//			for (cat in _unfilteredCategories.value!!) {
//				nameList.add(cat.categoryName)
//			}
//		}
//		_catNameList.postValue(nameList)
//		return nameList
//	}
