package com.example.e_commerce.wishlist

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Token
import com.example.domain.contract.repository.CartRepository
import com.example.domain.contract.repository.WishlistRepository
import com.example.domain.model.products.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(private val wishlistRepository: WishlistRepository,
                                            private  val cartRepository: CartRepository
):ViewModel() {

    val wishlist = mutableStateListOf<ProductItem?>()


    fun getWishlist(){

        viewModelScope.launch {

            val response = wishlistRepository.getWishlist(Token.token!!)

            if (response!=null && response.status.equals("success")){

                wishlist.clear()
                wishlist.addAll(response.data!!)
                wishlist.forEach {
                }
            }
        }
    }



    fun removeProductFromWishlist(product:ProductItem){

        viewModelScope.launch {

            wishlistRepository.removeProductFromWishlist(Token.token?:"",product.id!!)

            wishlist.remove(product)
        }
    }

    fun addToCart(product:ProductItem){

        viewModelScope.launch {

            cartRepository.addProductToCart(Token.token!!,product.id!!)

            removeProductFromWishlist(product)

        }

    }
}