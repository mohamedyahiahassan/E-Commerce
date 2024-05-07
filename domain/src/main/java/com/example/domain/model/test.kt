package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class Test(

	@field:SerializedName("statusMsg")
	val statusMsg: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
