package com.example.domain.model.wishlist

import com.example.domain.model.products.ProductItem
import com.google.gson.annotations.SerializedName

data class GetWishlistResponse(

	@field:SerializedName("data")
	val data: List<ProductItem?>? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)
