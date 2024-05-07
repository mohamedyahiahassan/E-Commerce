package com.example.data.model.userData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataDto(
	val password: String? = null,
	val phone: String? = null,
	val rePassword: String? = null,
	val name: String? = null,
	val email: String? = null
):Parcelable