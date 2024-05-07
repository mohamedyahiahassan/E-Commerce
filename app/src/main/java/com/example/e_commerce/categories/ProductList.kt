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

            items(viewModel.listOfProducts.size) {

                Box(
                    modifier = Modifier
                        .padding(bottom = 17.dp)
                        .size(220.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(3.dp, greyBlueBorderSideMenu, RoundedCornerShape(10.dp))
                        .background(Color.Transparent, RoundedCornerShape(10.dp))
                        .clickable {

                            viewModel.selectedProductDetails.value = viewModel.listOfProducts[it]!!

                            navigateToProductDetails(viewModel.selectedProductDetails.value!!)
                        }
                ) {


                    Column(
                    ) {
                        AsyncImage(
                            viewModel.listOfProducts[it]!!.imageCover,
                            contentDescription = "updateCartProduct image",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight(0.5f)
                        )

                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp, start = 8.dp, end = 8.dp)
                        ) {

                            Text(
                                text = viewModel.listOfProducts[it]!!.title?:"",
                                fontSize = 14.sp,
                                color = blueTextColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = viewModel.listOfProducts[it]!!.description?:"",
                                fontSize = 14.sp,
                                color = blueTextColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = "EGP ${viewModel.listOfProducts[it]!!.price?:""}",
                                fontSize = 14.sp,
                                color = blueTextColor,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Review (${viewModel.listOfProducts[it]!!.ratingsAverage})")
                                Image(
                                    painter = painterResource(id = R.drawable.star_rating),
                                    contentDescription = "rating star",
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .size(15.dp)
                                )
                            }


                        }


                    }



                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .padding(end = 6.dp, bottom = 10.dp, top = 6.dp)
                            .fillMaxSize(1f)
                    ) {

                        val favoriteClicked = remember {
                            mutableStateOf(false)
                        }
                        Image(
                            painter = if (favoriteClicked.value == false) painterResource(id = R.drawable.favorite_unselected_ic)
                            else painterResource(id = R.drawable.favorite_selected_ic),
                            contentDescription = "",
                            modifier = Modifier
                                .size(28.dp)
                                .clickable {

                                    if (favoriteClicked.value == true) {
                                        favoriteClicked.value = false
                                        viewModel.removeProductFromWishlist(viewModel.listOfProducts[it]!!.id?:"")


                                    } else if (favoriteClicked.value == false) {
                                        favoriteClicked.value = true
                                        viewModel.addProductToWishlist(viewModel.listOfProducts[it]!!.id?:"")
                                    }




                                }
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = painterResource(id = R.drawable.icon_plus_circle),
                            contentDescription = "",
                            modifier = Modifier
                                .size(28.dp)
                                .clickable {
                                    viewModel.selectedProductDetails.value = viewModel.listOfProducts[it]
                                           viewModel.addToCart()
                                },
                        )
                    }


                }

            }
        }
    }
}