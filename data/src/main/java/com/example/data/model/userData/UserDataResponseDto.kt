package com.example.data.model.userData

import com.google.gson.annotations.SerializedName

data class UserDataResponseDto(

	@field:SerializedName("statusMsg")
	val statusMsg: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)