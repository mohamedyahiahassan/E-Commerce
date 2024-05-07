package com.example.domain.model.products

import com.google.gson.annotations.SerializedName

data class ProductItemDTO(

    @field:SerializedName("sold")
	val sold: Int? = null,

    @field:SerializedName("images")
	val images: List<String?>? = null,

    @field:SerializedName("quantity")
	val quantity: Int? = null,

    @field:SerializedName("availableColors")
	val availableColors: List<Any?>? = null,

    @field:SerializedName("imageCover")
	val imageCover: String? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("title")
	val title: String? = null,

    @field:SerializedName("ratingsQuantity")
	val ratingsQuantity: Int? = null,

    @field:SerializedName("ratingsAverage")
	val ratingsAverage: Any? = null,

    @field:SerializedName("createdAt")
	val createdAt: String? = null,

    @field:SerializedName("price")
	val price: Int? = null,

    @field:SerializedName("_id")
	val _id: String? = null,

    @field:SerializedName("id")
	val id: String? = null,

    @field:SerializedName("subcategory")
	val subcategory: List<SubcategoryItemDTO?>? = null,

    @field:SerializedName("category")
	val category: CategoryDTO? = null,

    @field:SerializedName("brand")
	val brand: BrandDTO? = null,

    @field:SerializedName("slug")
	val slug: String? = null,

    @field:SerializedName("updatedAt")
	val updatedAt: String? = null,

    @field:SerializedName("priceAfterDiscount")
	val priceAfterDiscount: Int? = null,

    val __v:Any? = null
){

    fun toProductItem():ProductItem{

        return ProductItem(sold, images, quantity, availableColors, imageCover,
            description, title, ratingsQuantity, ratingsAverage, createdAt,
            price,
            _id,
            id,
            subcategory?.map {
                            it?.toSubcategoryItem()
            },
            category?.toCategory(),
            brand?.toBrand(),
            slug,
            updatedAt,
            priceAfterDiscount,
            __v)
    }
}