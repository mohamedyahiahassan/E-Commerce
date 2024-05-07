package com.example.domain.contract.repository

import com.example.domain.model.cart.AddProductToCartResponse
import com.example.domain.model.cart.GetCartResponse
import com.example.domain.model.cart.RemoveProductFromCartResponse
import com.example.domain.model.cart.UpdateProductQuantityResponse

interface CartRepository {

    suspend fun addProductToCart(token:String,productId:String):AddProductToCartResponse?

    suspend fun removeProductToCart(token:String,productId:String):RemoveProductFromCartResponse?

    suspend fun getCart(token:String):GetCartResponse?

    suspend fun updateProductQuantity(token:String,productId:String,count:String):UpdateProductQuantityResponse?

}