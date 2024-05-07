package com.example.e_commerce.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_commerce.R


@Composable
fun LoginTextField(textState:MutableState<String>, placeHolderText:String,keyboardOptions:KeyboardOptions,errorState:String?){

    var passwordVisibility = remember {
        mutableStateOf<Boolean>(false)
    }
    LaunchedEffect(key1 = Unit){
        if (keyboardOptions == KeyboardOptions(keyboardType = KeyboardType.Password)){

            passwordVisibility.value = true
        }

    }




        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                            textState.value = it

            },
            singleLine = true,

            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor= Color.Black,
            ),
            placeholder = {
                Text(text = placeHolderText)
            },
            keyboardOptions = keyboardOptions,

            visualTransformation = if (passwordVisibility.value == false)  VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (keyboardOptions == KeyboardOptions(keyboardType = KeyboardType.Password)) {
                    IconButton(onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    }) {
                        if (passwordVisibility.value == true) {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_visibility_off_24),
                                contentDescription = "show password"
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_visibility_24),
                                contentDescription = "hide password"
                            )
                        }


                    }
                }
            },
            supportingText = {
                            if (errorState!=null){
                                Text(text = errorState, color = Color.Red)
                            }
            },
            isError = if (errorState!=null) true else false,
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxWidth(1f)

            )




}