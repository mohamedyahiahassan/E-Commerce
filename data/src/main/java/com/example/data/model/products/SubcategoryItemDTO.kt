package com.example.domain.model.products

import com.google.gson.annotations.SerializedName

data class SubcategoryItemDTO(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
){

	fun toSubcategoryItem():SubcategoryItem{

	return 	SubcategoryItem(name,id,category, slug)
	}
}