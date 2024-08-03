package com.example.e_commerce.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.e_commerce.ui.theme.primaryBlue
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.e_commerce.ui.theme.blueTextColor
import com.example.e_commerce.ui.theme.greyBlueBorderSideMenu
import com.example.e_commerce.ui.theme.greySideMenu


@Composable
fun CategoriesScreenContent(
    viewModel: CategoriesViewModel = viewModel(),
    index: Int?,
    navigateToProductsList:(categoryId:String)->Unit

){

    LaunchedEffect(key1 = Unit) {

        if (index != null) {
            viewModel.selectedCategoryIndex.intValue = index
        }
    }

    LaunchedEffect(key1 = !viewModel.categoriesList.isNullOrEmpty() == true) {

        if (!viewModel.categoriesList.isNullOrEmpty()){

            viewModel.getSubCategories(viewModel.categoriesList[index?:0]?.id)
        }


    }

    Row(
        modifier = Modifier
            .padding(end = 17.dp)
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
    ) {

        sideMenu(viewModel)
        SubCategoriesGrid(viewModel,index,navigateToProductsList)
    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SubCategoriesGrid(viewModel: CategoriesViewModel,index: Int?,navigateToProductsList:(categoryId:String)->Unit) {

    if (!viewModel.categoriesList.isNullOrEmpty()) {

        Column(modifier = Modifier.padding(start = 20.dp)) {

            Text(
                text = viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]?.name.toString(),
                color = blueTextColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.30f)
            ) {


                AsyncImage(
                    model = viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]?.image,
                    contentDescription = "categoryDTO Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .clip(shape = RoundedCornerShape(10.dp)),
                )

                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 5.dp, bottom = 5.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(1f)
                ) {


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
                                navigateToProductsList(
                                    viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]!!.id
                                        ?: ""
                                )
                            }


                    )
                }
            }

            LazyColumn(
                modifier = Modifier.padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                    items(viewModel.selectedSubCategoriesList) {

                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .fillMaxWidth(1f)
                                .border(1.dp, greySideMenu, RoundedCornerShape(10.dp))
                                .clip(RoundedCornerShape(10.dp))) {

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = it?.name ?: "",
                                fontSize = 14.sp,
                                color = blueTextColor,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.CenterHorizontally)

                            )

                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
        }
    }
}


@Composable
fun sideMenu(viewModel: CategoriesViewModel) {

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
fun SideMenuItem(viewModel: CategoriesViewModel, index:Int){

    if (viewModel.selectedCategoryIndex.intValue == index){

        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(70.dp)
                .height(IntrinsicSize.Min)
                .background(Color.White)
                .clickable {

                    viewModel.selectedCategoryIndex.intValue = index

                    viewModel.getSubCategories(viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]?.id)

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

                    viewModel.getSubCategories(viewModel.categoriesList[viewModel.selectedCategoryIndex.intValue]?.id)


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

@SuppressLint("ModifierFactoryUnreferencedReceiver")
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


