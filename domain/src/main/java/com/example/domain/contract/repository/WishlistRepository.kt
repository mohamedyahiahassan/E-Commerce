package com.example.domain.contract.repository

import com.example.domain.model.wishlist.AddRemoveWishlistResponse
import com.example.domain.model.wishlist.GetWishlistResponse

interface WishlistRepository {

    suspend fun addProductToWishlist(token:String,productId:String):AddRemoveWishlistResponse?

    suspend fun removeProductFromWishlist(token:String,productId:String):AddRemoveWishlistResponse?

    suspend fun getWishlist(token:String): GetWishlistResponse?
}