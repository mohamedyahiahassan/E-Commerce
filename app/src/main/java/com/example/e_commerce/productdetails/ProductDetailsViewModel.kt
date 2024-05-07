package com.example.e_commerce.productdetails

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Token
import com.example.domain.contract.repository.CartRepository
import com.example.domain.model.products.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private  val cartRepository: CartRepository,

):ViewModel() {

    val selectedProductDetails = mutableStateOf<ProductItem?>(null)

    val selectedAmountOfProduct = mutableIntStateOf(1)

    val slideImage =  mutableStateOf<String>(selectedProductDetails.value?.images?.get(0).toString())


    fun calculateTotalAmount(price:Int):Int{


        return selectedAmountOfProduct.intValue * price

    }

    fun addToCart(){

        viewModelScope.launch {

            cartRepository.addProductToCart(Token.token!!,selectedProductDetails.value?.id?:"")

            cartRepository.updateProductQuantity(Token.token!!,selectedProductDetails.value?.id!!,selectedAmountOfProduct.value.toString())
        }

    }

}