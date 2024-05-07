package com.example.e_commerce

import android.app.Application
import android.content.Context
import com.example.data.connectivity.NetworkHandler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ECommerce:Application() {

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        NetworkHandler.context = this



    }
}