package com.prabin.saveoassignment.models

import com.google.gson.annotations.SerializedName

data class ImageModel(

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null
)