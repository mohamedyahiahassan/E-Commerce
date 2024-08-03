package com.example.e_commerce.home

import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_commerce.Constants
import com.example.e_commerce.R
import com.example.e_commerce.ui.theme.primaryBlue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.e_commerce.ui.theme.blueTextColor
import com.example.e_commerce.ui.theme.greyBlueBorderSideMenu
import com.example.e_commerce.ui.theme.greySideMenu
import com.example.e_commerce.utils.EcomBottomAppBar
import com.example.e_commerce.utils.EcomTopAppBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.products.ProductItem
import com.example.e_commerce.categories.CategoriesViewModel
import com.example.e_commerce.categories.SideMenuItem
import com.example.e_commerce.ui.theme.poppinsFont
import com.example.e_commerce.utils.DotsIndicator
import com.example.e_commerce.utils.ProductOverView


//@Preview(showSystemUi = true)
@Composable
fun HomeScreenContent(
    viewModel: CategoriesViewModel = viewModel(),
    onCategoryClick:(index:Int)->Unit,
    onViewAllClick:()->Unit,
    navigateToProductDetails:(productItem: ProductItem)->Unit){

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ){
        DiscountAds()

        CategoriesGrid(viewModel,onCategoryClick,onViewAllClick)

        ElectronicsProducts {
            navigateToProductDetails(it)
        }

    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DiscountAds(){

    val state = rememberPagerState()

    val slideImage = remember { mutableStateOf<Int>(R.drawable.speaker_dis) }


    Box (contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .padding(end = 17.dp)
            .fillMaxWidth(1f)) {

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth(1f),
            count = 3,
            state = state
        ) { page ->
            when (page) {

                0 -> {
                    slideImage.value = R.drawable.speaker_dis
                }

                1 -> {
                    slideImage.value = R.drawable.makeup_dis
                }

                2 -> {
                    slideImage.value = R.drawable.electronics_dis
                }
            }


            Image(
                painterResource(slideImage.value),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(1f),
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


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoriesGrid(viewModel: CategoriesViewModel,onCategoryClick:(index:Int)->Unit,onViewAllClick:()->Unit){
    
    LaunchedEffect(key1 = Unit){

        if (viewModel.categoriesList.isNullOrEmpty()){
            viewModel.getCategories()
        }

    }
    
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top=20.dp, bottom = 20.dp, end = 17.dp)
        ) {
        Text(
            text = "Categories",
            color = blueTextColor,
            fontSize = 18.sp,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.weight(1f))

        Text(text = "view all",
            color = blueTextColor,
            fontSize = 12.sp,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable {
                onViewAllClick()
            })
    }
    
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.height(280.dp)
            ,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        content = {

        items(viewModel.categoriesList.size){

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    onCategoryClick(it)
                }
            ){
                GlideImage(
                    model = viewModel.categoriesList[it]?.image,
                    contentDescription = viewModel.categoriesList[it]?.name,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp),
                    contentScale = ContentScale.Crop)

                Text(
                    text = viewModel.categoriesList[it]?.name?:"",
                    color = blueTextColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 15.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(90.dp)
            )

            }

        }
        
    })
}



@Composable
fun ElectronicsProducts(
    navigateToProductDetails:(productItem: ProductItem)->Unit
){

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 10.dp, end = 17.dp)
    ) {
        Text(
            text = "Electronics",
            color = blueTextColor,
            fontSize = 18.sp,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Medium)

    }

    val viewModel = hiltViewModel<CategoriesViewModel>()
    
    LaunchedEffect(key1 = Unit) {

        viewModel.categoryId.value = "6439d2d167d9aa4ca970649f"

        viewModel.getProductsInSelectedCategory()
    }

    LazyRow(

        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ){

        items(viewModel.listOfProducts){

            ProductOverView(
                image = it?.imageCover!!,
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







