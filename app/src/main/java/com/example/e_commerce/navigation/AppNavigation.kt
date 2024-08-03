package com.example.e_commerce.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.windowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.e_commerce.AccountScreen
import com.example.e_commerce.AccountScreenContent
import com.example.e_commerce.CategoriesScreen
import com.example.e_commerce.HomeNavigator
import com.example.e_commerce.HomeScreen
import com.example.e_commerce.ProductsListScreen
import com.example.e_commerce.WishlistScreen
import com.example.e_commerce.categories.CategoriesScreenContent
import com.example.e_commerce.categories.CategoriesViewModel
import com.example.e_commerce.categories.ProductListContent
import com.example.e_commerce.home.HomeScreenContent
import com.example.e_commerce.utils.EcomBottomAppBar
import com.example.e_commerce.utils.EcomTopAppBar
import com.example.e_commerce.wishlist.WishListContent
import com.example.e_commerce.wishlist.WishlistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationTabs(navigator: HomeNavigator) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.padding(top = windowInsets.asPaddingValues().calculateTopPadding()),
        topBar = {
            EcomTopAppBar(){
                navigator.navigateToCart()
            }},
        bottomBar = {
            EcomBottomAppBar(currentDestination) { route ->

                navController.navigate(route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            }
        }

    ) {
        Column(
            modifier =
            Modifier
                .padding(
                    top = it.calculateTopPadding() + 20.dp,
                    start = 17.dp,
                    bottom = it.calculateBottomPadding()
                )
                .fillMaxWidth(1f)
        ) {

            NavHost(navController = navController, startDestination = HomeScreen().route){

                composable(HomeScreen().route){

                    val viewModel = hiltViewModel<CategoriesViewModel>()

                    HomeScreenContent(viewModel,
                        onCategoryClick ={ index->
                            navController.navigate(
                                route = "${CategoriesScreen().route}/${index}")
                        },
                        onViewAllClick =   {

                            navController.navigate("${CategoriesScreen().route}/${0}")
                        },
                        navigateToProductDetails = { productItem->

                            navigator.navigateToProductDetails(productItem)
                        })


                }

                composable(
                    route = "${CategoriesScreen().route}/{index}",
                    arguments = listOf(navArgument("index"){

                        type = NavType.IntType
                    })
                ){
                    val viewModel = hiltViewModel<CategoriesViewModel>()

                    val index = it.arguments?.getInt("index")

                    BackHandler {

                        navController.navigateUp()
                    }
                    CategoriesScreenContent(viewModel,index){categoryId->

                        navController.navigate("${ProductsListScreen().route}/${categoryId}")
                    }

                }

                composable(
                    route = "${ProductsListScreen().route}/{categoryId}",
                    arguments = listOf(navArgument("categoryId"){

                        type = NavType.StringType
                    })
                ){ it ->
                    val viewModel = hiltViewModel<CategoriesViewModel>()

                    val categoryId = it.arguments?.getString("categoryId")

                    ProductListContent(
                        viewModel,
                        categoryId,
                        navigateToProductDetails = {productItem->

                            navigator.navigateToProductDetails(productItem)
                        })

                }

                composable(
                    route = WishlistScreen().route

                ){
                    val viewModel = hiltViewModel<WishlistViewModel>()

                    BackHandler {

                        navController.navigateUp()
                    }

                    WishListContent(viewModel)



                }

                composable(
                    route = AccountScreen().route

                ){

                    BackHandler {

                        navController.navigateUp()
                    }

                    AccountScreenContent()



                }

            }


        }


    }


}