package com.rotemyanco.brandysestore.ui.categoryProducts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rotemyanco.brandysestore.App
import com.rotemyanco.brandysestore.models.BaseProduct
import kotlinx.coroutines.launch


class CategoryProductsViewModel(application: Application) : AndroidViewModel(application) {

	private val _productsByCat: MutableLiveData<List<BaseProduct>?> =
		MutableLiveData<List<BaseProduct>?>()

	val productsByCat: LiveData<List<BaseProduct>?>
		get() = _productsByCat


	fun getAllProductsByCategoryId(id: String) {
		viewModelScope.launch {
			val products = App.repo.getAllProductsByCategoryId(id).docs
			_productsByCat.postValue(products)
		}
	}
}