package com.prabin.saveoassignment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseModel(

	@field:SerializedName("score")
	val score: Any? = null,

	@field:SerializedName("show")
	val show: ShowModel? = null
) : Serializable