package br.com.wfariasgoes.network.response

import com.google.gson.annotations.SerializedName

data class ResponseData (

	@SerializedName("objects") val objects : List<Objects>
)