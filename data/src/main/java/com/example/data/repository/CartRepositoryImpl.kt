package com.example.data.repository

import com.example.data.dataSource.OnlineDataSourceImpl
import com.example.domain.contract.repository.CartRepository
import com.example.domain.model.cart.AddProductToCartResponse
import com.example.domain.model.cart.GetCartResponse
import com.example.domain.model.cart.RemoveProductFromCartResponse
import com.example.domain.model.cart.UpdateProductQuantityResponse
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private  val onlineDataSourceImpl: OnlineDataSourceImpl):CartRepository {
    override suspend fun addProductToCart(
        token: String,
        productId: String
    ): AddProductToCartResponse? {

       return onlineDataSourceImpl.addProductToCart(token, productId)
    }

    override suspend fun removeProductToCart(
        token: String,
        productId: String
    ): RemoveProductFromCartResponse? {

        return onlineDataSourceImpl.removeProductToCart(token, productId)
    }

    override suspend fun getCart(token: String): GetCartResponse? {

        return onlineDataSourceImpl.getCart(token)
    }

    override suspend fun updateProductQuantity(
        token: String,
        productId:String,
        count: String
    ): UpdateProductQuantityResponse? {

        return onlineDataSourceImpl.updateProductQuantity(token,productId, count)
    }
}