package br.com.wfariasgoes.network

import br.com.wfariasgoes.network.response.ResponseData
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {

    @GET("/looke/assets.json")
    fun getMovies(): Observable<ResponseData>

}