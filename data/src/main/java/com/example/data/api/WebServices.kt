package com.example.data.api

import com.example.data.model.SignInResponseDTO
import com.example.data.model.subCategories.SubCategoriesResponseDTO
import com.example.data.model.wishlist.AddRemoveWishlistResponseDTO
import com.example.data.model.wishlist.GetWishlistResponseDTO
import com.example.domain.model.UserData
import com.example.domain.model.UserDataResponse
import com.example.domain.model.cart.AddProductToCartResponse
import com.example.domain.model.cart.GetCartResponse
import com.example.domain.model.cart.RemoveProductFromCartResponse
import com.example.domain.model.cart.UpdateProductQuantityResponse
import com.example.domain.model.categories.CategoriesResponseDTO
import com.example.domain.model.products.ProductsInCategoryResponseDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {


    @POST("api/v1/auth/signup")
    suspend  fun signUp(@Body userData: UserData): UserDataResponse

    @FormUrlEncoded
    @POST("api/v1/auth/signin")
    suspend fun signIn(@Field ("email") email:String,
                       @Field ("password") password:String):SignInResponseDTO

    @GET("api/v1/categories")
    suspend fun getAllCategories(): CategoriesResponseDTO?

    @GET("api/v1/categories/{id}/subcategories")
    suspend fun getAllSubCategoriesOnCategory(@Path("id") id:String): SubCategoriesResponseDTO?

    @GET("api/v1/products/")
    suspend fun getProductsInCategory(@Query ("category[in]") categoryId:String):ProductsInCategoryResponseDTO?

    @FormUrlEncoded
    @POST("api/v1/wishlist")
    suspend fun addProductToWishlist(@Header ("token") token:String,
                                     @Field ("productId") productId:String):AddRemoveWishlistResponseDTO?

    @DELETE("api/v1/wishlist/{productId}")
    suspend fun removeProductFromWishlist(@Header ("token") token:String,
                                          @Path("productId") productId:String,
                                          ):AddRemoveWishlistResponseDTO?

    @GET("api/v1/wishlist")
    suspend fun getWishlist(@Header ("token") token:String):GetWishlistResponseDTO?

    @FormUrlEncoded
    @POST("api/v1/cart")
    suspend fun addProductToCart(@Header ("token") token: String,
                                 @Field ("productId") productId: String):AddProductToCartResponse?

    @FormUrlEncoded
    @PUT("api/v1/cart/{productId}")
    suspend fun updateProductCount(@Header ("token") token: String,
                                   @Path ("productId")productId:String,
                                 @Field ("count") count: String):UpdateProductQuantityResponse?


    @GET("api/v1/cart")
    suspend fun getCart(@Header ("token") token:String):GetCartResponse?


    @DELETE("api/v1/cart/{productId}")
    suspend fun deleteProduct(@Header ("token") token: String,
                              @Path ("productId") productId: String):RemoveProductFromCartResponse?

}