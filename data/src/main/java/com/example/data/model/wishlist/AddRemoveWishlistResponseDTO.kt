package com.example.data.model.wishlist

import com.example.domain.model.wishlist.AddRemoveWishlistResponse
import com.google.gson.annotations.SerializedName

data class AddRemoveWishlistResponseDTO(


	val data: List<String?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
){

	fun toWishListResponse(): AddRemoveWishlistResponse {

		return AddRemoveWishlistResponse(data, message, status)
	}
}
