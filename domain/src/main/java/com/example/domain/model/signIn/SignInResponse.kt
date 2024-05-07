package com.example.domain.model.signIn

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInResponse(

    val statusMsg: String? = null,

    val message: String? = null,

    val user: User? = null,

    val token: String? = null
):Parcelable

@Parcelize
data class User(

	val role: String? = null,

	val name: String? = null,

	val email: String? = null
):Parcelable
