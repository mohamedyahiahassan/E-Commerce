package com.example.e_commerce

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.e_commerce.login.LoginActivity
import com.example.e_commerce.ui.theme.ECommerceTheme
import com.example.e_commerce.ui.theme.blueSplashScreen

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            /*statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT)

             */
                )


        super.onCreate(savedInstanceState)
        setContent {
            ECommerceTheme {

                SplashScreenContent()

                Handler(Looper.getMainLooper()).postDelayed({

                    var intent = Intent(this@SplashScreen, LoginActivity::class.java)
                    startActivity(intent)
                    finish()


                },2000)

            }
        }
    }
}


@Composable
fun SplashScreenContent(){

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(blueSplashScreen),

    ) {

        Image(painter = painterResource(id = R.drawable.ellipse_top),
            contentDescription ="eclipse white",
            modifier = Modifier
                .fillMaxWidth(1f),
            contentScale = ContentScale.FillWidth
            )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.route_logo),
            contentDescription ="route logo",
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillWidth,

        )

        Spacer(modifier = Modifier.weight(1f))


        Image(painter = painterResource(id = R.drawable.ellipse_down),
            contentDescription ="eclipse white",
            modifier = Modifier
                .fillMaxWidth(1f)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillWidth
        )

    }
}
