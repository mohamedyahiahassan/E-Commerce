package com.example.data.model.wishlist

import com.example.domain.model.products.ProductItemDTO
import com.example.domain.model.wishlist.GetWishlistResponse
import com.google.gson.annotations.SerializedName

data class GetWishlistResponseDTO(


	val data: List<ProductItemDTO?>? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
){

	fun toGetWishlistResponse(): GetWishlistResponse {

		return GetWishlistResponse(data?.map {
											it?.toProductItem()
		},count, status)
	}
}
