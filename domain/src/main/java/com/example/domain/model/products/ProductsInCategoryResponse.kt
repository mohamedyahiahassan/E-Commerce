package com.example.domain.model.products

data class ProductsInCategoryResponse(

    val metadata: ProductsMetadata? = null,

    val data: List<ProductItem?>? = null,

    val results: Int? = null
)