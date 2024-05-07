package com.example.data.model.subCategories

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SubCategoriesResponse(

	val metadata: Metadata? = null,

	val data: List<SubCategoryItem?>? = null,

	val results: Int? = null
) : Parcelable