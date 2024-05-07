package com.example.domain.model.categories

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CategoriesResponseDTO(

	val metadata: Metadata? = null,

	val data: List<CategoryItemDTO?>? = null,

	val results: Int? = null,

	val statusMsg:String? = null,

	val message: String? =null
) : Parcelable