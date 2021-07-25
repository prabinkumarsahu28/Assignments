package com.prabin.saveoassignment.model

import com.google.gson.annotations.SerializedName

data class 	MoviesModel(

	@field:SerializedName("score")
	val score: Any? = null,

	@field:SerializedName("show")
	val show: ShowModel? = null
)