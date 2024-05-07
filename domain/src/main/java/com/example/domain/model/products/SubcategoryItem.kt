package com.example.domain.model.products

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubcategoryItem(

	val name: String? = null,

	@field:SerializedName("_id")
	val _id: String? = null,

	val category: String? = null,

	val slug: String? = null
):Parcelable