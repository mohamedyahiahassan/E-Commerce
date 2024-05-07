package com.example.domain.model.cart

import com.google.gson.annotations.SerializedName

data class AddProductToCartResponse(

	@field:SerializedName("data")
	val data: AddProductData? = null,

	@field:SerializedName("numOfCartItems")
	val numOfCartItems: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class AddProductsItem(

	@field:SerializedName("updateCartProduct")
	val product: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null
)

data class AddProductData(

	@field:SerializedName("cartOwner")
	val cartOwner: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("totalCartPrice")
	val totalCartPrice: Int? = null,

	@field:SerializedName("__v")
	val __v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("products")
	val products: List<AddProductsItem?>? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
