package com.kevu.kevalassign

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ProductRepository(private val productDao: ProductDao) {

    val allProducts:LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun delete(product: Product){
        productDao.delete(product)
    }

    suspend fun insert(product: Product) {
        productDao.insert(product)
    }
}
