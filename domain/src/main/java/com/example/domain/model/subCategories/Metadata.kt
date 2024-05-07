package com.example.data.model.subCategories

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Metadata(

	val numberOfPages: Int? = null,

	val limit: Int? = null,

	val currentPage: Int? = null
) : Parcelable