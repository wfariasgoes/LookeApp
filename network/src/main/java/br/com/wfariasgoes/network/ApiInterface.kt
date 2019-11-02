package br.com.wfariasgoes.network

import br.com.wfariasgoes.network.response.ResponseData
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {

    @GET("desafio-dev-android.appspot.com/o/assets.json?alt=media&token=964a35bb-53d0-45aa-a3dd-ecad72a2f14c")
    fun getMovies(): Observable<ResponseData>

}