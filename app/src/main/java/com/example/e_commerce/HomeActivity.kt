package com.example.e_commerce

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.e_commerce.ui.theme.ECommerceTheme
import com.example.domain.model.products.ProductItem
import com.example.e_commerce.cart.CartDetailsActivity
import com.example.e_commerce.navigation.ApplicationTabs
import com.example.e_commerce.productdetails.ProductDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity(), HomeNavigator {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ECommerceTheme {

                ApplicationTabs(this)
            }
        }
    }

    override fun navigateToProductDetails(productItem: ProductItem) {

        val intent = Intent(this,ProductDetails::class.java)
        intent.putExtra("updateCartProduct",productItem)
        startActivity(intent)
    }

    override fun navigateToCart() {
        val intent = Intent(this,CartDetailsActivity::class.java)
        startActivity(intent)
    }
}

interface HomeNavigator{

   fun navigateToProductDetails(productItem: ProductItem)
   fun navigateToCart()
}
