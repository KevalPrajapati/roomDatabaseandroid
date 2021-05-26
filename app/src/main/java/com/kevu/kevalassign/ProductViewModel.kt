package com.kevu.kevalassign

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kevu.kevalassign.Product
import kotlinx.coroutines.*

public class ProductViewModel(application: Application):AndroidViewModel(application){
    val repository:ProductRepository
    val allProducts:LiveData<List<Product>>
    init {
        val dao = ProductRoomDatabase.getDatabase(application).getProductDao()
         repository  = ProductRepository(dao)
        allProducts = repository.allProducts
    }

    fun deleteProduct(product: Product) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(product)
    }
    fun insertProduct(product: Product) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(product)
    }
}