package com.example.e_commerce.categories

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_commerce.R
import com.example.e_commerce.ui.theme.blueTextColor
import com.example.e_commerce.ui.theme.greyBlueBorderSideMenu
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.domain.model.products.ProductItem
import com.example.e_commerce.utils.ProductOverView

@Composable
fun ProductListContent(
    viewModel: CategoriesViewModel = viewModel(),
    categoryId:String?,
    navigateToProductDetails:(productItem:ProductItem)->Unit) {



    LaunchedEffect(key1 = Unit) {

        if (categoryId != null) {

            viewModel.categoryId.value = categoryId
        }
        viewModel.getProductsInSelectedCategory()
    }

    if (!viewModel.listOfProducts.isNullOrEmpty()) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(end = 17.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalArrangement = Arrangement.spacedBy(17.dp)
        ) {

            items(viewModel.listOfProducts) {

               if (it!=null){

                   ProductOverView(
                       image = it.imageCover!!,
                       name = it.title!!,
                       desc = it.description!!,
                       price =it.price.toString() ,
                       review = it.ratingsAverage.toString(),
                       openProductDetails = {

                           viewModel.selectedProductDetails.value = it

                           navigateToProductDetails(it)
                       },
                       addedToFavourite = {

                           viewModel.addProductToWishlist(it.id?:"")
                       },
                       removeFromFavourite = {

                           viewModel.removeProductFromWishlist(it.id?:"")
                       },
                       addToCart = {

                           viewModel.selectedProductDetails.value = it
                           viewModel.addToCart()

                       })
               }

            }
        }
    }
}

