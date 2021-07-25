package com.prabin.saveoassignment.models

import com.google.gson.annotations.SerializedName

data class ExternalsModel(

	@field:SerializedName("thetvdb")
	val thetvdb: Int? = null,

	@field:SerializedName("imdb")
	val imdb: String? = null,

	@field:SerializedName("tvrage")
	val tvrage: Int? = null
)