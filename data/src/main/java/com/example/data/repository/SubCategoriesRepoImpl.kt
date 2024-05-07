package com.example.data.repository

import com.example.data.model.subCategories.SubCategoryItem
import com.example.domain.contract.dataSource.OnlineDataSource
import com.example.domain.contract.repository.SubCategoriesRepository
import javax.inject.Inject

class SubCategoriesRepoImpl @Inject constructor(private val onlineDataSource: OnlineDataSource): SubCategoriesRepository {

    override suspend fun getAllSubCategoriesOnCategory(id: String): List<SubCategoryItem?>? {

        return onlineDataSource.getAllSubCategoriesOnCategory(id)
    }
}