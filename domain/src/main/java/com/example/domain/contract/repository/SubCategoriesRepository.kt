package com.example.domain.contract.repository

import com.example.data.model.subCategories.SubCategoriesResponse
import com.example.data.model.subCategories.SubCategoryItem

interface SubCategoriesRepository {

    suspend fun getAllSubCategoriesOnCategory(id: String): List<SubCategoryItem?>?

}