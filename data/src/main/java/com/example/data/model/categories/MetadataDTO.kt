package com.example.domain.model.categories

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class MetadataDTO(

	val numberOfPages: Int? = null,

	val limit: Int? = null,

	val currentPage: Int? = null
) : Parcelable