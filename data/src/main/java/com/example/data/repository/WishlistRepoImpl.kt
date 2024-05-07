package com.example.data.repository

import com.example.data.dataSource.OnlineDataSourceImpl
import com.example.domain.contract.repository.WishlistRepository
import com.example.domain.model.wishlist.AddRemoveWishlistResponse
import com.example.domain.model.wishlist.GetWishlistResponse
import javax.inject.Inject

class WishlistRepoImpl @Inject constructor(private val onlineDataSourceImpl: OnlineDataSourceImpl) :WishlistRepository {

    override suspend fun addProductToWishlist(
        token: String,
        productId: String
    ): AddRemoveWishlistResponse? {

      return onlineDataSourceImpl.addProductToWishlist(token, productId)
    }

    override suspend fun removeProductFromWishlist(
        token: String,
        productId: String
    ): AddRemoveWishlistResponse? {

      return  onlineDataSourceImpl.removeProductFromWishlist(token, productId)
    }

    override suspend fun getWishlist(token: String): GetWishlistResponse? {

        return onlineDataSourceImpl.getWishlist(token)
    }


}