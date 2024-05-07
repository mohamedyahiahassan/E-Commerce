package com.example.domain.model.products

import com.google.gson.annotations.SerializedName

data class ProductsMetadataDTO(

	@field:SerializedName("numberOfPages")
	val numberOfPages: Int? = null,

	@field:SerializedName("nextPage")
	val nextPage: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
){
	fun toProductsMetaData():ProductsMetadata{

		return ProductsMetadata(numberOfPages, nextPage, limit, currentPage)
	}
}