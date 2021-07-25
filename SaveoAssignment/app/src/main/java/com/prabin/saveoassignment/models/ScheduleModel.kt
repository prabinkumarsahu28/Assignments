package com.prabin.saveoassignment.models

import com.google.gson.annotations.SerializedName

data class ScheduleModel(

	@field:SerializedName("days")
	val days: List<String?>? = null,

	@field:SerializedName("time")
	val time: String? = null
)