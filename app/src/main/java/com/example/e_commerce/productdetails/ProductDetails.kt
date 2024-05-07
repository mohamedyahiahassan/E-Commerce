package com.example.e_commerce.productdetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.products.ProductItem
import com.example.e_commerce.R
import com.example.e_commerce.ui.theme.blueTextColor
import com.example.e_commerce.ui.theme.greyBlueBorderSideMenu
import com.example.e_commerce.ui.theme.greyText
import com.example.e_commerce.ui.theme.primaryBlue
import com.example.e_commerce.utils.DotsIndicator
import com.example.e_commerce.utils.ProductDetailsTopAppBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.e_commerce.ui.theme.ECommerceTheme

@AndroidEntryPoint
class ProductDetails : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productItem:ProductItem? = intent.getParcelableExtra("updateCartProduct")

        setContent {
            ECommerceTheme {

                Scaffold (

                    topBar = { ProductDetailsTopAppBar(
                        onBackPressedButton = {

                            onBackPressed()
                        }
                    ) }
                ){

                    ProductDetailsContent(it,productItem)
                }

            }
        }
    }
}


//@Preview(showSystemUi = true)
@Composable
fun ProductDetailsContent(paddingValues: PaddingValues, productItem: ProductItem?,viewModel: ProductDetailsViewModel = viewModel()){

    LaunchedEffect(key1 = Unit){

        viewModel.selectedProductDetails.value = productItem
    }

    Column(
        modifier = Modifier
            .padding(start = 17.dp, end = 17.dp, top = paddingValues.calculateTopPadding())
            .fillMaxSize(1f)

    ) {

        ProductImagesSlider(viewModel)

        Row (
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(1f)


        ){
            Text(
                text = productItem?.title?:"",
                fontSize = 18.sp,
                color = blueTextColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(200.dp))
            
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "EGP ${productItem?.price}",
                fontSize = 18.sp,
                color = blueTextColor)

        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 17.dp)
                .fillMaxWidth(1f)

        ){
            Text(
                text = "${productItem?.sold} Sold",
                fontSize = 15.sp,
                color = blueTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(100.dp)
                    .height(30.dp)
                    .border(1.dp, greyBlueBorderSideMenu, RoundedCornerShape(15.dp))
                    .wrapContentHeight(align = Alignment.CenterVertically))

            Image(
                painter = painterResource(id = R.drawable.star_rating),
                contentDescription = "rating star",
                modifier = Modifier
                    .padding(start = 8.dp, end = 4.dp)
                    .size(15.dp)
            )

            Text(
                text = "${productItem?.ratingsAverage} (${productItem?.ratingsQuantity})",
                fontSize = 14.sp,
                color = blueTextColor)

            Spacer(modifier = Modifier.weight(1f))

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(125.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(primaryBlue, RoundedCornerShape(25.dp))
            ){
                IconButton(
                    onClick = {

                        if (viewModel.selectedAmountOfProduct.value>1){

                            viewModel.selectedAmountOfProduct.value--
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
                    text = viewModel.selectedAmountOfProduct.value.toString(),
                    fontSize = 20.sp,
                    color = Color.White)

                Spacer(modifier = Modifier.weight(1f))

                IconButton(

                    onClick = {
                        viewModel.selectedAmountOfProduct.value++
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

        Text(
            text = "Description",
            fontSize = 18.sp,
            color = blueTextColor,
            fontWeight = FontWeight.Bold)

        Text(
            text = productItem?.description?:"",
            fontSize = 14.sp,
            color = greyText)

       /* Text(
            text = "Size",
            fontSize = 18.sp,
            color = blueTextColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))

        Text(
            text = "Color",
            fontSize = 18.sp,
            color = blueTextColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))

        */

        Row (modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(1f)

        ){

            Column {
                Text(
                    text = "Total Price",
                    fontSize = 18.sp,
                    color = greyText,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "EGP ${viewModel.calculateTotalAmount(productItem?.price?:0)}",
                    fontSize = 18.sp,
                    color = blueTextColor,
                    fontWeight = FontWeight.Bold)


            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {

                          viewModel.addToCart()
                          },
                colors = ButtonDefaults.buttonColors(primaryBlue),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(50.dp)

            ) {
                
                Image(
                    painter = painterResource(id = R.drawable.add_to_cart_b1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Add to cart",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.weight(1f))
            }

        }


    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductImagesSlider(viewModel: ProductDetailsViewModel){

    val state = rememberPagerState()

    //val slideImage = remember { mutableStateOf<Int>(R.drawable.product_image_test1) }


    Box (contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.3f)) {

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth(1f),
            count = viewModel.selectedProductDetails.value?.images?.size?:0,
            state = state
        ) { page ->
            when (page) {

                0 -> {
                    viewModel.slideImage.value =
                        viewModel.selectedProductDetails.value!!.images?.get(0).toString()
                }

                1 -> {
                    viewModel.slideImage.value = viewModel.selectedProductDetails.value!!.images?.get(1).toString()
                }

                2 -> {
                    viewModel.slideImage.value = viewModel.selectedProductDetails.value!!.images?.get(2).toString()
                }
            }


            AsyncImage(
                viewModel.slideImage.value,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, greyBlueBorderSideMenu, RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillWidth
            )
        }
        DotsIndicator(
            totalDots = 3,
            selectedIndex = state.currentPage,
            selectedColor = primaryBlue,
            unSelectedColor = Color.White,
        )
    }

}