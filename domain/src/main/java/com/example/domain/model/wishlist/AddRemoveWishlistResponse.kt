package com.example.domain.model.wishlist

import com.google.gson.annotations.SerializedName

data class AddRemoveWishlistResponse(

	val data: List<String?>? = null,

	val message: String? = null,

	val status: String? = null
)
