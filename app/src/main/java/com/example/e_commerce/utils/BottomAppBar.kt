package com.example.e_commerce.utils

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.e_commerce.AccountScreen
import com.example.e_commerce.CategoriesScreen
import com.example.e_commerce.Constants
import com.example.e_commerce.HomeScreen
import com.example.e_commerce.ProductsListScreen
import com.example.e_commerce.WishlistScreen
import com.example.e_commerce.ui.theme.primaryBlue

@Composable
fun EcomBottomAppBar(currentDestination: NavDestination?,navigateToDestination:(route:String)->Unit){

        NavigationBar(
            modifier = Modifier.height(85.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            containerColor = primaryBlue
        ) {

            NavigationBarItem(
                selected =  currentDestination?.hierarchy?.any {
                    it.route == HomeScreen().route } == true,

                onClick = {
                    navigateToDestination(HomeScreen().route)

                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = primaryBlue),
                icon = {

                    if (currentDestination?.hierarchy?.any {
                            it.route == HomeScreen().route } == true) {
                        Image(
                            painter = painterResource(id = HomeScreen().navBarTabItem!!.selectedIcon),
                            contentDescription = HomeScreen().navBarTabItem!!.title,
                            modifier = Modifier.size(40.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = HomeScreen().navBarTabItem!!.unSelectedIcon),
                            contentDescription = HomeScreen().navBarTabItem!!.title,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                })

            NavigationBarItem(
                selected =  currentDestination?.hierarchy?.any {
                    val index = 0
                    val categoryId = ""
                    it.route == "${CategoriesScreen().route}/${index}"

                } == true,

                onClick = {

                   navigateToDestination("${CategoriesScreen().route}/${0}")

                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = primaryBlue),
                icon = {

                    if (currentDestination?.hierarchy?.any {
                            it.route == "${CategoriesScreen().route}/{index}"

                                    || it.route == "${ProductsListScreen().route}/{categoryId}"} == true) {

                        Image(
                            painter = painterResource(id = CategoriesScreen().navBarTabItem!!.selectedIcon),
                            contentDescription = CategoriesScreen().navBarTabItem!!.title,
                            modifier = Modifier.size(40.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = CategoriesScreen().navBarTabItem!!.unSelectedIcon),
                            contentDescription = CategoriesScreen().navBarTabItem!!.title,
                            modifier = Modifier.size(40.dp)

                        )
                    }

                })


            NavigationBarItem(
                selected =  currentDestination?.hierarchy?.any {
                    val index = 0
                    val categoryId = ""
                    it.route == WishlistScreen().route

                } == true,

                onClick = {

                    navigateToDestination(WishlistScreen().route)
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = primaryBlue),
                icon = {

                    if (currentDestination?.hierarchy?.any {
                            it.route == WishlistScreen().route } == true) {

                        Image(
                            painter = painterResource(id = WishlistScreen().navBarTabItem!!.selectedIcon),
                            contentDescription = CategoriesScreen().navBarTabItem!!.title,
                            modifier = Modifier.size(40.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = WishlistScreen().navBarTabItem!!.unSelectedIcon),
                            contentDescription = CategoriesScreen().navBarTabItem!!.title,
                            modifier = Modifier.size(40.dp)

                        )
                    }

                })

            NavigationBarItem(
                selected =  currentDestination?.hierarchy?.any {
                    val index = 0
                    val categoryId = ""
                    it.route == AccountScreen().route

                } == true,

                onClick = {

                    navigateToDestination(AccountScreen().route)
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = primaryBlue),
                icon = {

                    if (currentDestination?.hierarchy?.any {
                            it.route == AccountScreen().route } == true) {

                        Image(
                            painter = painterResource(id = AccountScreen().navBarTabItem!!.selectedIcon),
                            contentDescription = AccountScreen().navBarTabItem!!.title,
                            modifier = Modifier.size(40.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = AccountScreen().navBarTabItem!!.unSelectedIcon),
                            contentDescription = AccountScreen().navBarTabItem!!.title,
                            modifier = Modifier.size(40.dp)

                        )
                    }

                })
        }
}
