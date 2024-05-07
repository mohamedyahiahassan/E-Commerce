package com.example.e_commerce.categories

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_commerce.home.HomeViewModel
import com.example.e_commerce.ui.theme.primaryBlue
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import com.bumptech.glide.integration.compose.rememberGlidePreloadingData
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerce.ui.theme.blueTextColor
import com.example.e_commerce.ui.theme.greyBlueBorderSideMenu
import com.example.e_commerce.ui.theme.greySideMenu



@Composable
fun CategoriesScreenContent(viewModel: HomeViewModel = viewModel(),index: Int?,navigateToProductsList:(categoryId:String)->Unit){

    Row(
        modifier = Modifier
            .padding( end = 17.dp)
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
    ) {

        if (index != null) {
            viewModel.selectedCategoryIndex.intValue = index
        }
        sideMenu(viewModel)
        SubCategoriesGrid(viewModel,index,navigateToProductsList)
    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SubCategoriesGrid(viewModel: HomeViewModel,index: Int?,navigateToProductsList:(categoryId:String)->Unit){

/*
    LaunchedEffect(key1 = Unit){

        viewModel.selectedCategoryIndex.intValue = index!!

        viewModel.getCategories()

        if (!viewModel.categoriesList.isNullOrEmpty()) {

            viewModel.onCategoryClick(viewModel.categoriesList[index!!]?.id)
        }
    }

 */


    if (!viewModel.categoriesList.isNullOrEmpty()){

      /*  LaunchedEffect(key1 = Unit){

           viewModel.selectedCategoryIndex.intValue = index!!

        viewModel.onCategoryClick(viewModel.categoriesList[index!!]?.id)

        }

       */



    Column(modifier = Modifier.padding(start = 20.dp)) {
        
        Text(
            text = viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]?.name.toString(),
            color = blueTextColor,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 20.dp))

        Box(modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.30f)){


            AsyncImage(model = viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]?.image,
                contentDescription = "categoryDTO Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(shape = RoundedCornerShape(10.dp)),
                )

            Column(modifier = Modifier
                .padding(start = 20.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxHeight()
                .fillMaxWidth(1f)) {


                Text(
                    text = viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]?.name.toString(),
                    fontSize = 16.sp,
                    color = Color.White,
                )
                
                Spacer(modifier = Modifier.weight(0.75f))


                Text(
                    text = "Shop Now",
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(primaryBlue)
                        .width(150.dp)
                        .height(40.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .clickable {
                            navigateToProductsList(viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]!!.id?:"")
                        }


                )
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.padding(top = 20.dp),

            content = {

                items(viewModel.selectedSubCategoriesList.size){

                    Column {

                        Text(text = viewModel.selectedSubCategoriesList[it]?.name.toString(),
                            fontSize = 14.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(1.dp))
                                .background(primaryBlue)
                                .width(80.dp)
                                .heightIn(min = 80.dp, max = 160.dp)
                                .wrapContentHeight(align = Alignment.CenterVertically)


                        )
                    }

                }

            })
      }






    }



}


@Composable
fun sideMenu(viewModel: HomeViewModel) {

    LaunchedEffect(key1 = Unit){

        viewModel.getCategories()

    }



        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 10.dp))
                .background(greySideMenu)
                .topBorder(3.dp, greyBlueBorderSideMenu, 10.dp)
                .fillMaxWidth(0.34f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(top = 1.5.dp, start = 1.5.dp)
                .clip(RoundedCornerShape(topStart = 10.dp))

        ) {
            repeat(viewModel.categoriesList.size) {


               SideMenuItem(viewModel,it)




            }
        }
}

@Composable
fun SideMenuItem(viewModel: HomeViewModel, index:Int){



    if (viewModel.selectedCategoryIndex.intValue == index){

        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(70.dp)
                .height(IntrinsicSize.Min)
                .background(Color.White)
                .clickable {

                    viewModel.selectedCategoryIndex.intValue = index

                    viewModel.onCategoryClick(viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]?.id)

                }
        ) {
            Divider(
                color = primaryBlue,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
                    .width(5.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(
                text = viewModel.categoriesList[index]?.name.toString(),
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(1f),
                fontSize = 16.sp,
                color = blueTextColor
            )
        }
    } else {

        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(Color.Transparent)
                .height(70.dp)
                .clickable {

                    viewModel.selectedCategoryIndex.intValue = index

                    viewModel.onCategoryClick(viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]?.id)


                }
        ) {


            Text(
                text = viewModel.categoriesList[index]?.name.toString(),
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(1f)
                    ,
                fontSize = 17.sp,
                color = blueTextColor

            )
        }
    }



}

fun Modifier.topBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = 0f, y = cornerRadiusPx),
                strokeWidth = strokeWidthPx
            )

            drawArc(
                color = color,
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset.Zero,
                size = Size(cornerRadiusPx * 2, cornerRadiusPx * 2),
                style = Stroke(width = strokeWidthPx)
            )

            drawLine(
                color = color,
                start = Offset(x = cornerRadiusPx, y = 0f),
                end = Offset(x = width, y = 0f),
                strokeWidth = strokeWidthPx
            )
        }
    }
)


