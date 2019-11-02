package br.com.wfariasgoes.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiBuilder {
    companion object {
        private val apiInterface: ApiInterface? = null
        fun getWebService(): ApiInterface {
            if (apiInterface != null) {
                return apiInterface
            }

            var interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            var builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
            builder.connectTimeout(300, TimeUnit.SECONDS)
            builder.readTimeout(80, TimeUnit.SECONDS)
            builder.writeTimeout(90, TimeUnit.SECONDS)

            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}