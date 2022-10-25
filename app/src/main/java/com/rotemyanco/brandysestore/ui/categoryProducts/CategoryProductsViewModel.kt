package com.rotemyanco.brandysestore.ui.categoryProducts

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rotemyanco.brandysestore.App
import com.rotemyanco.brandysestore.models.BaseProduct
import kotlinx.coroutines.launch

class CategoryProductsViewModel(application: Application) : AndroidViewModel(application) {

    private val logTag = " CategoryProductsViewModel "


    private val _productsByCat: MutableLiveData<List<BaseProduct>?> =
        MutableLiveData<List<BaseProduct>?>()

    val productsByCat: LiveData<List<BaseProduct>?>
        get() = _productsByCat


    private val _strCatId: MutableLiveData<String> = MutableLiveData<String>()

    val strCatId: LiveData<String>
        get() = _strCatId


    fun setCatId(id: String) {
        _strCatId.postValue(id)
    }

    fun getAllProductsByCategoryId() {
        viewModelScope.launch {
            Log.d(logTag, " ----- getAllProductsByCategoryId()        ->      strCatId = ${strCatId.value.toString()} -----")
            val products = App.repo.getAllProductsByCategoryId(strCatId.value.toString()).docs
            Log.d(logTag, "getAllProductsByCategoryId:        -> ${products[0]}")
            _productsByCat.postValue(products)
        }
        Log.d(logTag, " ----- getAllProductsByCategoryId()        ->      strCatId = ${strCatId.value.toString()} ---")
    }

    init {
        Log.d( "---- CategoryProductsViewModel -----", " ----  init:     strCatId = ${strCatId.value.toString()} -----")
    }

}

