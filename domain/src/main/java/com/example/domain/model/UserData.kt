package com.example.domain.model

import android.os.Parcelable



data class UserData(
	val name: String? = null,
	val email: String? = null,
	val password: String? = null,
	val rePassword: String? = null,
	val phone: String? = null,

)