package br.com.wfariasgoes.network.response

import com.google.gson.annotations.SerializedName

data class Objects (

	@SerializedName("name") val name : String,
	@SerializedName("bg") val bg : String,
	@SerializedName("im") val im : String,
	@SerializedName("sg") val sg : String
)