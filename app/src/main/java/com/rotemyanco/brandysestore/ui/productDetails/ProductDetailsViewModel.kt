package com.rotemyanco.brandysestore.ui.productDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rotemyanco.brandysestore.App
import com.rotemyanco.brandysestore.models.BaseProduct
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ProductDetailsViewModel(application: Application) :
	AndroidViewModel(application) {


	private val _baseProduct: MutableLiveData<BaseProduct> =
		MutableLiveData<BaseProduct>()

	val baseProduct: LiveData<BaseProduct>
		get() = _baseProduct


	private val _baseProductId: MutableLiveData<String> = MutableLiveData<String>()

	val baseProductId: LiveData<String>
		get() = _baseProductId


	fun setBaseProductId(id: String) {
		_baseProductId.postValue(id)
	}


	init {

//		runBlocking {
//			val baseProduct = App.repo.getBaseProductById(baseProductId.value.toString())
//			_baseProduct.postValue(baseProduct)
//		}


		viewModelScope.launch {
			val baseProduct = App.repo.getBaseProductById(baseProductId.value.toString())
			_baseProduct.postValue(baseProduct)
		}
	}


}