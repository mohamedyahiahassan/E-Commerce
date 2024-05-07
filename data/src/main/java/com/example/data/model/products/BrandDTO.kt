package com.example.domain.model.products

import com.google.gson.annotations.SerializedName

data class BrandDTO(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) {

	fun toBrand():Brand{
		return Brand(image, name, id, slug)
	}
}