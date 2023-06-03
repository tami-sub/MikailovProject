package com.example.mikailovproject.network.retrofit

import com.example.mikailovproject.shared.randomfact.core.data.remote.RemoteApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule() {

    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    fun getOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    fun getRemoteApi(retrofit: Retrofit): RemoteApi = retrofit.create(RemoteApi::class.java)

    companion object {
        private const val BASE_URL = "https://v2.convertapi.com/"
    }
}
