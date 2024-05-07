package com.example.domain.model.cart

import com.example.domain.model.products.Brand
import com.example.domain.model.products.Category
import com.example.domain.model.products.SubcategoryItem
import com.google.gson.annotations.SerializedName

data class GetCartResponse(

	@field:SerializedName("data")
	val data: GetCartData? = null,

	@field:SerializedName("numOfCartItems")
	val numOfCartItems: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class GetCartProduct(

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("imageCover")
	val imageCover: String? = null,

	@field:SerializedName("_id")
	val _id: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("subcategory")
	val subcategory: List<SubcategoryItem?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("brand")
	val brand: Brand? = null,

	@field:SerializedName("ratingsAverage")
	val ratingsAverage: Any? = null
)



data class GetCartData(

	@field:SerializedName("cartOwner")
	val cartOwner: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("totalCartPrice")
	val totalCartPrice: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("products")
	val products: List<GetCartProductsItem?>? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class GetCartProductsItem(

	@field:SerializedName("product")
	val product: GetCartProduct? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
