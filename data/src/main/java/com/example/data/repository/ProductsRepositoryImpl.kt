package com.example.data.repository

import com.example.data.dataSource.OnlineDataSourceImpl
import com.example.domain.contract.repository.ProductsRepository
import com.example.domain.model.categories.CategoryItem
import com.example.domain.model.products.ProductItem
import com.example.domain.model.products.ProductsInCategoryResponse
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private  val onlineDataSourceImpl: OnlineDataSourceImpl):ProductsRepository {

    override suspend fun getProductsInCategory(categoryId: String): ProductsInCategoryResponse? {

        return onlineDataSourceImpl.getProductsInCategory(categoryId)
    }

    override suspend fun getAllCategories(): List<CategoryItem?>? {
        return onlineDataSourceImpl.getAllCategories()
    }

}