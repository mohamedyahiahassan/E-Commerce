package com.example.e_commerce.cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.windowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.e_commerce.R
import com.example.e_commerce.ui.theme.blueTextColor
import com.example.e_commerce.ui.theme.greyBlueBorderSideMenu
import com.example.e_commerce.ui.theme.primaryBlue
import com.example.e_commerce.utils.ProductDetailsTopAppBar
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.cart.GetCartProductsItem
import com.example.e_commerce.ui.theme.ECommerceTheme
import com.example.e_commerce.ui.theme.greyText


@AndroidEntryPoint
class CartDetailsActivity: ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ECommerceTheme {

                Scaffold (
                    modifier = Modifier.fillMaxHeight(),

                    topBar = { ProductDetailsTopAppBar(
                        onBackPressedButton = {
                        onBackPressed()
                    }) }
                ){

                    CartContent(it)
                }

            }
        }
    }
}


@Composable
fun CartContent(paddingValues: PaddingValues,viewModel:CartViewModel = viewModel()) {


    LaunchedEffect(key1 = Unit) {

        viewModel.getCart()
    }

    if (!viewModel.listOfProducts.isNullOrEmpty()) {

        Column(modifier = Modifier.fillMaxSize(1f)) {

            LazyColumn(
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = 17.dp,
                        end = 17.dp
                    )
                    .fillMaxHeight(0.8F)
            ) {

                items(viewModel.listOfProducts, key = { item -> item?.id!!}) {

                    if (it != null) {
                        CartItem(
                            cartItem = it,
                            removeProductFromCArt = {

                                viewModel.removeProductFromCart(it.product?.id!!)
                        },
                            updateProductQuantity = {quantity->

                                viewModel.updateProductQuantityInCart(it.product!!,quantity)

                            })
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .padding(17.dp)
                    .fillMaxWidth(1f)

            ) {

                Column {
                    Text(
                        text = "Total Price",
                        fontSize = 18.sp,
                        color = greyText,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "EGP ${viewModel.totalCartPrice.value}",
                        fontSize = 18.sp,
                        color = blueTextColor,
                        fontWeight = FontWeight.Bold
                    )


                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(primaryBlue),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .height(50.dp)

                ) {

                    Text(
                        text = "Check Out",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }

            }
        }
    }
}

@Composable
fun CartItem(
    cartItem:GetCartProductsItem,
    removeProductFromCArt:()->Unit,
    updateProductQuantity:(quantity:Int)->Unit){

    val selectedAmountOfProduct = remember  {mutableStateOf(cartItem.count)}

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
            cartItem.product?.imageCover,
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
                .padding(10.dp)) {


            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {

                Text(
                    text = cartItem.product?.title ?: "",
                    fontSize = 18.sp,
                    color = blueTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.delete_from_cart),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {

                            removeProductFromCArt()
                        })


            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),

                ) {

                Text(
                    text = "EGP ${cartItem.price}",
                    fontSize = 18.sp,
                    color = blueTextColor
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(125.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(primaryBlue, RoundedCornerShape(25.dp))
                ) {
                    IconButton(
                        onClick = {

                            if (selectedAmountOfProduct.value!! > 1) {

                                selectedAmountOfProduct.value = selectedAmountOfProduct.value!! - 1

                                updateProductQuantity(selectedAmountOfProduct.value!!)

                            }


                        }) {

                        Image(
                            painter = painterResource(id = R.drawable.icon_minus_product),
                            contentDescription = "",
                            modifier = Modifier
                                .size(25.dp),
                        )

                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${selectedAmountOfProduct.value}",
                        fontSize = 20.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(

                        onClick = {
                            selectedAmountOfProduct.value = selectedAmountOfProduct.value!! + 1

                            updateProductQuantity(selectedAmountOfProduct.value!!)

                        }) {

                        Image(
                            painter = painterResource(id = R.drawable.icon_add_product),
                            contentDescription = "",
                            modifier = Modifier
                                .size(25.dp),
                        )

                    }

                }

            }
        }
    }
}
