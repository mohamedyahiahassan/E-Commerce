package com.example.domain.contract.dataSource

import com.example.data.model.subCategories.SubCategoryItem
import com.example.domain.model.signIn.SignInResponse
import com.example.domain.model.UserData
import com.example.domain.model.UserDataResponse
import com.example.domain.model.cart.AddProductToCartResponse
import com.example.domain.model.cart.GetCartResponse
import com.example.domain.model.cart.RemoveProductFromCartResponse
import com.example.domain.model.cart.UpdateProductQuantityResponse
import com.example.domain.model.categories.CategoryItem
import com.example.domain.model.products.ProductsInCategoryResponse
import com.example.domain.model.wishlist.AddRemoveWishlistResponse
import com.example.domain.model.wishlist.GetWishlistResponse

interface OnlineDataSource {

    suspend fun signUp(userData: UserData): UserDataResponse

    suspend fun signIn(userData: UserData): SignInResponse

    suspend fun getAllCategories(): List<CategoryItem?>?

    suspend fun getAllSubCategoriesOnCategory(id: String): List<SubCategoryItem?>?

    suspend fun getProductsInCategory(categoryId:String): ProductsInCategoryResponse?

    suspend fun addProductToWishlist(token:String,productId:String): AddRemoveWishlistResponse?

    suspend fun removeProductFromWishlist(token:String,productId:String):AddRemoveWishlistResponse?

    suspend fun getWishlist(token:String): GetWishlistResponse?

    suspend fun addProductToCart(token:String,productId:String):AddProductToCartResponse?

    suspend fun removeProductToCart(token:String,productId:String):RemoveProductFromCartResponse?

    suspend fun getCart(token:String):GetCartResponse?

    suspend fun updateProductQuantity(token:String,productId:String,count:String):UpdateProductQuantityResponse?
}