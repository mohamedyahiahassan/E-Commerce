package com.example.e_commerce.wishlist

import android.util.Log
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.e_commerce.ui.theme.primaryBlue
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.domain.model.products.ProductItem

@Preview(showSystemUi = true)
@Composable

fun WishListContent(viewModel: WishlistViewModel = viewModel()) {


    LaunchedEffect(key1 = Unit) {

        viewModel.getWishlist()
    }


    if (!viewModel.wishlist.isNullOrEmpty()) {

        LazyColumn(modifier = Modifier.padding(end = 17.dp)) {


            items(viewModel.wishlist, key = { productItem-> productItem?.id!! }) {


                if (it != null) {
                    WishlistItem(
                        productItem = it,
                        deleteFromWishlist = {
                            viewModel.removeProductFromWishlist(it)

                    },
                        addToCart = {
                            viewModel.addToCart(it)
                        })
                }
            }


        }
    }
}

@Composable
fun WishlistItem(productItem: ProductItem,deleteFromWishlist:()->Unit,addToCart:()->Unit){
    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(1f)
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, greyBlueBorderSideMenu, RoundedCornerShape(10.dp))
            .background(Color.White, RoundedCornerShape(10.dp))

    ) {

        AsyncImage(
            productItem.imageCover,
            contentDescription = "wishlist updateCartProduct image",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(2.dp, greyBlueBorderSideMenu, RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            Modifier
                .fillMaxHeight(1f)
                .padding(10.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {

                Text(
                    text = productItem.title ?: "",
                    fontSize = 18.sp,
                    color = blueTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.favorite_selected_ic),
                    contentDescription = "",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {

                            deleteFromWishlist()
                        }
                )




            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),

                ) {

                Text(
                    text = "EGP ${productItem.price}",
                    fontSize = 18.sp,
                    color = blueTextColor
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {

                        addToCart()
                    },
                    colors = ButtonDefaults.buttonColors(primaryBlue),
                    shape = RoundedCornerShape(15.dp)


                ) {

                    Text(
                        text = "Add to Cart",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

            }


        }
    }
}