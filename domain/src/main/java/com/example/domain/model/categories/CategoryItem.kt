package com.example.domain.model.categories

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CategoryItem(

	val image: String? = null,

	val createdAt: String? = null,

	val name: String? = null,

	val id: String? = null,

	val slug: String? = null,

	val updatedAt: String? = null
) : Parcelable