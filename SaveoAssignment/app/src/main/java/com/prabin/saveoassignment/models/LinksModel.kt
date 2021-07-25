package com.prabin.saveoassignment.models

import com.google.gson.annotations.SerializedName

data class LinksModel(

	@field:SerializedName("self")
	val self: SelfModel? = null,

	@field:SerializedName("previousepisode")
	val previousepisode: PreviousepisodeModel? = null
)