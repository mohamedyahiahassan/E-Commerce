package com.example.data.model.subCategories

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class SubCategoryItemDTO(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("categoryDTO")
	val category: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
) : Parcelable {

	fun toSubCategoryItem():SubCategoryItem{

	 return 	SubCategoryItem(createdAt, name, id, category, slug, updatedAt)
	}
}