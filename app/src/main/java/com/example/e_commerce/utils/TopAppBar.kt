package com.example.e_commerce.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.e_commerce.R
import com.example.e_commerce.ui.theme.primaryBlue

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EcomTopAppBar(navigateToCart:()->Unit){

    var searchQuery by remember {

        mutableStateOf("")
    }

    var isActive by remember {

        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .padding(start = 17.dp, end = 17.dp, top = 5.dp)
        .fillMaxWidth(1f)) {
        Image(
            painter = painterResource(id = R.drawable.route_top_app_bar),
            contentDescription = "logo_top_App_bar",
            modifier = Modifier
                .fillMaxWidth(0.20f),
            contentScale = ContentScale.FillWidth)

        Row(modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(1f),
            verticalAlignment = Alignment.CenterVertically

        ) {

            OutlinedTextField(
                value = searchQuery ,
                onValueChange ={
                    searchQuery = it
                },
                shape = RoundedCornerShape(30.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.88f)
                    .fillMaxHeight(0.071f)

                ,
                leadingIcon = {

                    Image(painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "search_icon")

                },
                placeholder = {

                    Text(text = "What do you search for?")
                },
                colors = OutlinedTextFieldDefaults.colors(

                    focusedContainerColor= Color.Transparent,
                    unfocusedContainerColor= Color.Transparent,
                    disabledContainerColor= Color.Transparent,
                    focusedBorderColor= primaryBlue,
                    unfocusedBorderColor = primaryBlue,
                    disabledBorderColor = primaryBlue
                )

            )

            Spacer(modifier = Modifier.weight(1f))

            Image(painter = painterResource(id = R.drawable.icon_shopping_cart),
                contentDescription = "cart_icon",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {

                        navigateToCart()
                    })
        }


    }

}