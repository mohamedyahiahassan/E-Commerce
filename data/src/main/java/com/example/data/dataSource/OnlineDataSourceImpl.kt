package com.example.data.dataSource

import android.util.Log
import com.example.data.api.WebServices
import com.example.data.connectivity.NetworkHandler
import com.example.data.model.subCategories.SubCategoryItem
import com.example.domain.contract.dataSource.OnlineDataSource
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
import javax.inject.Inject

class OnlineDataSourceImpl @Inject constructor(private val webServices: WebServices): OnlineDataSource {

    override suspend fun signUp(userData: UserData): UserDataResponse {

        return try {

            webServices.signUp(userData)


        } catch (e:Exception){

            throw e
        }
    }

    override suspend fun signIn(userData: UserData): SignInResponse {

        var response: SignInResponse? = null

        if (NetworkHandler.isNetworkAvailable()==false){

            return SignInResponse(message = "Check your internet connection")
        }



        return try {
             response =  webServices.signIn(userData.email.toString(),userData.password.toString()).toSignInResponse()

            response
        } catch (e:Exception){


            SignInResponse(message = "Invalid email or password")
        }

    }

    override suspend fun getAllCategories(): List<CategoryItem?>? {



        return try {

            webServices.getAllCategories()?.data?.map {

                it?.toCategory()
            }


        } catch (e:Exception){



           throw e
        }
    }

    override suspend fun getAllSubCategoriesOnCategory(id: String): List<SubCategoryItem?>? {

        return try{
            webServices.getAllSubCategoriesOnCategory(id)?.data?.map {

                it?.toSubCategoryItem()
            }

        } catch (e:Exception){

            throw e
        }
    }

    override suspend fun getProductsInCategory(categoryId: String): ProductsInCategoryResponse? {

        val response: ProductsInCategoryResponse?

        return try {
          webServices.getProductsInCategory(categoryId)?.toProductsInCategoryResponse()
        }catch (e:Exception){

            throw e

        }
    }

    override suspend fun addProductToWishlist(
        token: String,
        productId: String
    ): AddRemoveWishlistResponse? {

        return try {

             val response = webServices.addProductToWishlist(token, productId)?.toWishListResponse()



            response
        } catch (e:Exception){

            throw e
        }
    }

    override suspend fun removeProductFromWishlist(
        token: String,
        productId: String
    ): AddRemoveWishlistResponse? {

        return try {

            webServices.removeProductFromWishlist(token,productId)?.toWishListResponse()
        } catch (e:Exception){

           throw e
        }
    }

    override suspend fun getWishlist(token: String): GetWishlistResponse? {

        return try {

          val response =  webServices.getWishlist(token)?.toGetWishlistResponse()


            response
        } catch (e:Exception){

            throw e
        }
    }

    override suspend fun addProductToCart(
        token: String,
        productId: String
    ): AddProductToCartResponse? {

        return try {

          val response =  webServices.addProductToCart(token, productId)





            response
        } catch (e:Exception){

            throw e
        }

    }

    override suspend fun removeProductToCart(
        token: String,
        productId: String
    ): RemoveProductFromCartResponse? {

        return try {

            webServices.deleteProduct(token, productId)

        } catch (e:Exception){

            throw e
        }

    }

    override suspend fun getCart(token: String): GetCartResponse? {

        return try {

         val response =   webServices.getCart(token)


            response
        } catch (e:Exception){

            throw e
        }

    }

    override suspend fun updateProductQuantity(
        token: String,
        productId:String,
        count: String
    ): UpdateProductQuantityResponse? {

        return try {

            webServices.updateProductCount(token,productId, count)

        } catch (e:Exception){

            throw e
        }
    }


}