package com.example.e_commerce.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.ui.theme.ECommerceTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_commerce.SignInScreen
import com.example.e_commerce.SignUpScreen
import com.example.e_commerce.HomeActivity


@AndroidEntryPoint
class LoginActivity : ComponentActivity(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ECommerceTheme {
                // A surface container using the 'background' color from the theme
              LoginContent(this)
            }
        }


    }

    override fun navigateToHome(token: String) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("token",token)
        startActivity(intent)
        finish()
    }
}

@Composable
fun LoginContent(navigator: Navigator){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SignInScreen().route

    ){
        composable(SignInScreen().route){
            val viewModel = hiltViewModel<LoginViewModel>()

            SignInScreenContent(
                viewModel,
                navigateToSignUp = {
                navController.navigate(SignUpScreen().route)
            },
                navigateToHome = {


                    navigator.navigateToHome(it)

                })
        }
        composable(SignUpScreen().route,){
            val viewModel = hiltViewModel<LoginViewModel>()
            SignUpScreenContent(viewModel)
        }
    }
}

interface Navigator {

    fun navigateToHome(token: String)
}