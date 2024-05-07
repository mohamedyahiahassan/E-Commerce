package com.example.e_commerce

import com.example.data.model.NavBarTabItem

object Constants {

    val navBarItemsList = listOf(

        NavBarTabItem("Home",R.drawable.selected_home_icon_navbar,R.drawable.unselected_home_icon),
        NavBarTabItem("Categories",R.drawable.selected_categories_icon_navbar,R.drawable.unselected_categories_icon),
    NavBarTabItem("Favorites",R.drawable.selected_favorites_icon_navbar,R.drawable.unselected_favorite_icon),
        NavBarTabItem("Account",R.drawable.selected_account_icon_navbar,R.drawable.unselected_account_icon)
    )

    val listOfScreens = listOf(

        HomeScreen(),
        CategoriesScreen(),
        WishlistScreen(),
        AccountScreen()

    )
}