package com.example.domain.contract.repository

import com.example.domain.model.products.ProductItem
import com.example.domain.model.products.ProductsInCategoryResponse
import retrofit2.http.Query

interface ProductsRepository {
    suspend fun getProductsInCategory(categoryId:String): ProductsInCategoryResponse?




}