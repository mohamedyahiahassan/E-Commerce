package com.example.data.model.subCategories

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SubCategoryItem(

	val createdAt: String? = null,

	val name: String? = null,

	val id: String? = null,

	val category: String? = null,

	val slug: String? = null,

	val updatedAt: String? = null
) : Parcelable {

}