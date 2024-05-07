package com.example.domain.model.categories

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class CategoryItemDTO(

	val image: String? = null,

	val createdAt: String? = null,

	val name: String? = null,

	@SerializedName("_id")
	val id: String? = null,

	val slug: String? = null,

	val updatedAt: String? = null
) : Parcelable {

	fun toCategory():CategoryItem{

		return CategoryItem(image, createdAt, name, id, slug, updatedAt)
	}
}