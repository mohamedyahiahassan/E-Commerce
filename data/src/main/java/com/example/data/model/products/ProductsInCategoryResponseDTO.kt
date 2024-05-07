package com.example.domain.model.products

import com.google.gson.annotations.SerializedName

data class ProductsInCategoryResponseDTO(

	@field:SerializedName("metadata")
	val metadata: ProductsMetadataDTO? = null,

	val data: List<ProductItemDTO?>? = null,

	@field:SerializedName("results")
	val results: Int? = null
) {

	fun toProductsInCategoryResponse():ProductsInCategoryResponse{

		return 	ProductsInCategoryResponse(metadata?.toProductsMetaData(),data?.map {
															 it?.toProductItem()
		},results)
	}
}