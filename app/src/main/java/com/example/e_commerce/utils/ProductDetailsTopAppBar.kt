package com.example.e_commerce.utils

import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.e_commerce.R
import com.example.e_commerce.ui.theme.blueTextColor
import com.example.e_commerce.ui.theme.primaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun ProductDetailsTopAppBar(onBackPressedButton:()->Unit){


    CenterAlignedTopAppBar(

        title = {
            Text(
                text = "Cart",
                color = blueTextColor
            )
        },
        navigationIcon = {
            IconButton(onClick = {

               onBackPressedButton()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description",
                    tint = primaryBlue
                )
            }
        },
        actions = {

            IconButton(onClick = { /* do something */ }) {
                Image(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Localized description",
                    modifier = Modifier.size(30.dp)
                )
            }

            IconButton(onClick = { /* do something */ }) {
                Image(
                    painter = painterResource(id = R.drawable.icon_shopping_cart),
                    contentDescription = "Localized description",
                    modifier = Modifier.size(24.dp)
                )
            }
        },





        )
}