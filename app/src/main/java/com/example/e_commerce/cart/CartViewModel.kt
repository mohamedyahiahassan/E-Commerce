package com.example.e_commerce.cart

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Token
import com.example.domain.contract.repository.CartRepository
import com.example.domain.model.cart.GetCartProductsItem
import com.example.domain.model.products.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel@Inject constructor(private  val cartRepository: CartRepository):ViewModel() {

    val totalCartPrice = mutableStateOf<Int?>(null)

    val listOfProducts = mutableStateListOf<GetCartProductsItem?>()

    val numberOfCartItems = mutableStateOf<Int?>(null)


    fun getCart(){


    viewModelScope.launch {

        val response = cartRepository.getCart(Token.token?:"")

        if (response!=null){

            totalCartPrice.value = response.data?.totalCartPrice

            response.data?.products?.let { listOfProducts.addAll(it)}

            numberOfCartItems.value = response.numOfCartItems



        }
    }
}

    fun removeProductFromCart(productId:String){

        viewModelScope.launch {

            cartRepository.removeProductToCart(Token.token!!,productId)
        }
    }

    fun updateProductQuantityInCart(product:GetCartProductsItem,quantity:Int){

        viewModelScope.launch {
            cartRepository.updateProductQuantity(Token.token!!,product.id!!,quantity.toString())

        }

    }


}