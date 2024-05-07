package com.example.e_commerce.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.example.e_commerce.R
import com.example.e_commerce.ui.theme.primaryBlue
import com.example.e_commerce.utils.LoginButton
import com.example.e_commerce.utils.LoginText
import com.example.e_commerce.utils.LoginTextField
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_commerce.utils.LoadingScreen


@Composable
fun SignInScreenContent(viewModel: LoginViewModel= viewModel(), navigateToSignUp:()->Unit,navigateToHome: (token:String) -> Unit) {

val lifecycle = LocalLifecycleOwner.current
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
            Spacer(modifier = Modifier.fillMaxHeight(0.09f))

            LoginText(textContent = "Welcome Back to Route", textSize = 27, bold = true)

            LoginText(textContent = "Please sign in with your mail", textSize = 16, bold = false)

            Spacer(modifier = Modifier.fillMaxHeight(0.042f))

            LoginText(textContent = "E-mail", textSize = 18, bold = false)

            Spacer(modifier = Modifier.fillMaxHeight(0.025f))

            LoginTextField(
                textState = viewModel.emailState,
                placeHolderText = "enter your email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                null
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.042f))

            LoginText(textContent = "Password", textSize = 18, bold = false)

            Spacer(modifier = Modifier.fillMaxHeight(0.025f))

            LoginTextField(
                textState = viewModel.passwordState,
                placeHolderText = "enter your password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                null
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.025f))

            LoginText(
                textContent = "Forgot password", textSize = 18, bold = false,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .align(Alignment.End)
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.1f))

            LoginText(
                textContent = viewModel.SignInErrorState.value ?: "",
                textSize = 18,
                bold = true,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.Red
            )

            LoginButton("Login",

                onLoginButtonClick = {

                    viewModel.isLoading.value = true

                    viewModel.SignInErrorState.value = null

                    viewModel.signIn()

                    viewModel.isSuccessful.observe(lifecycle, {

                        if (it == true){

                            navigateToHome(viewModel.response.value?.token?:"")
                        }
                    })



                })

            Spacer(modifier = Modifier.fillMaxHeight(0.06f))

            LoginText(textContent = "Don't have an account? Create Account",
                textSize = 18,
                bold = false,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        viewModel.emailState.value = ""
                        viewModel.passwordState.value = ""
                        navigateToSignUp()
                    }

            )

            LoadingScreen(isLoading = viewModel.isLoading)
        }
}
