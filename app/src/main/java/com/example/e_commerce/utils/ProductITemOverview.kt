package com.example.e_commerce.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.e_commerce.R
import com.example.e_commerce.ui.theme.blueTextColor
import com.example.e_commerce.ui.theme.greyBlueBorderSideMenu

@Composable
fun ProductOverView(
    image:String,
    name:String,
    desc:String,
    price:String,
    review:String,
    openProductDetails:()->Unit,
    addedToFavourite:()->Unit,
    removeFromFavourite:()->Unit,
    addToCart:()->Unit){

    Box(
        modifier = Modifier
            .padding(bottom = 17.dp)
            .height(237.dp)
            .aspectRatio(0.8f)
            .clip(RoundedCornerShape(10.dp))
            .border(3.dp, greyBlueBorderSideMenu, RoundedCornerShape(10.dp))
            .background(Color.Transparent, RoundedCornerShape(10.dp))
            .clickable {

                openProductDetails()
            }
    ) {


        Column(
        ) {
            AsyncImage(
                image,
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
                    text = name,
                    fontSize = 14.sp,
                    color = blueTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = desc,
                    fontSize = 14.sp,
                    color = blueTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "EGP ${price}}",
                    fontSize = 14.sp,
                    color = blueTextColor,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Review (${review})")
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

                            removeFromFavourite()

                        } else if (favoriteClicked.value == false) {
                            favoriteClicked.value = true

                            addedToFavourite()
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
                        addToCart()
                    },
            )
        }


    }

}