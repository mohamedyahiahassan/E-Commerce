package com.example.e_commerce.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.e_commerce.R
import com.example.e_commerce.ui.theme.primaryBlue
import com.example.e_commerce.utils.LoginButton
import com.example.e_commerce.utils.LoginText
import com.example.e_commerce.utils.LoginTextField
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.UserData

@Preview
@Composable
fun SignUpScreenContent(viewModel: LoginViewModel = viewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(primaryBlue)
    ) {

        Spacer(modifier = Modifier.fillMaxHeight(0.09f))
        Image(
            painter = painterResource(id = R.drawable.route_transparent_white),
            contentDescription = "route logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(0.551f)
                .align(Alignment.CenterHorizontally)

        )
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))

        LoginText(textContent = "Full Name", textSize = 18, bold =false )

        Spacer(modifier = Modifier.fillMaxHeight(0.025f))

        LoginTextField(textState = viewModel.nameState, placeHolderText = "enter your full name", keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),viewModel.nameErrorState.value)

        LoginText(textContent = "Mobile Number", textSize = 18, bold =false )

        Spacer(modifier = Modifier.fillMaxHeight(0.025f))

        LoginTextField(textState = viewModel.phoneState, placeHolderText = "enter your mobile no.", keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),viewModel.phoneErrorState.value)

        LoginText(textContent = "E-mail address", textSize = 18, bold =false )

        Spacer(modifier = Modifier.fillMaxHeight(0.025f))

        LoginTextField(textState = viewModel.emailState, placeHolderText = "enter your email address", keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),viewModel.emailErrorState.value)

        LoginText(textContent = "Password", textSize = 18, bold =false )

        Spacer(modifier = Modifier.fillMaxHeight(0.025f))

        LoginTextField(textState = viewModel.passwordState, placeHolderText = "enter your password", keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),viewModel.passwordErrorState.value)

        Spacer(modifier = Modifier.fillMaxHeight(0.05f))

        LoginButton(text = "Sign up",{

            viewModel.signUp()


    })
}
}