package com.example.e_commerce

import com.example.data.model.NavBarTabItem

open class Screen(val route:String,val navBarTabItem: NavBarTabItem? = null)

class SignInScreen : Screen("sign_in")
class SignUpScreen : Screen("sign_up")
class HomeScreen: Screen("home_screen",Constants.navBarItemsList[0])
class CategoriesScreen : Screen("categories_screen", Constants.navBarItemsList[1])
class ProductsListScreen : Screen("product_lis_screen")
class WishlistScreen: Screen("favorites_screen",Constants.navBarItemsList[2])
class AccountScreen:Screen("account_screen",Constants.navBarItemsList[3])

