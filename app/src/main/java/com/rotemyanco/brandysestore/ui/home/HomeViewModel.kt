package com.rotemyanco.brandysestore.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rotemyanco.brandysestore.models.Category
import com.rotemyanco.brandysestore.retrofit.CategoriesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>().apply {

    }

    val categories: LiveData<List<Category>>
        get() = _categories


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = CategoriesRepo.getAllProductCategories(application)
            _categories.postValue(categories)
        }
    }

}