package com.example.e_commerce.categories

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Token
import com.example.domain.contract.repository.CartRepository
import com.example.domain.contract.repository.ProductsRepository
import com.example.domain.contract.repository.WishlistRepository
import com.example.domain.model.products.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val productsRepository: ProductsRepository,
    private val wishlistRepository: WishlistRepository,
    private val cartRepository: CartRepository):ViewModel() {


    val categoryId = mutableStateOf<String?>(null)

    var listOfProducts = mutableStateListOf<ProductItem?>()

    val selectedProductDetails = mutableStateOf<ProductItem?>(null)


    fun getProductsInSelectedCategory(){

        viewModelScope.launch {

            val response  = productsRepository.getProductsInCategory(categoryId.value!!)!!.data


            if (response != null) {
                listOfProducts.addAll(response)
            }
        }
    }

    fun addProductToWishlist(productId:String){

        viewModelScope.launch {

            wishlistRepository.addProductToWishlist(Token.token?:"",productId)





        }
    }

    fun removeProductFromWishlist(productId:String){

        viewModelScope.launch {

            wishlistRepository.removeProductFromWishlist(Token.token?:"",productId)





        }
    }

    fun addToCart(){

        viewModelScope.launch {




            cartRepository.addProductToCart(Token.token!!,selectedProductDetails.value?.id?:"")

        }

    }

}