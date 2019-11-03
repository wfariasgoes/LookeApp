package br.com.wfariasgoes.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Objects (

	@SerializedName("name") val name : String,
	@SerializedName("bg") val bg : String,
	@SerializedName("im") val im : String,
	@SerializedName("sg") val sg : String
): Serializable