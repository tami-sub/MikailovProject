package com.example.mikailovproject.network.retrofit

import android.app.Application
import com.example.mikailovproject.network.retrofit.Utils.BASE_URL
import com.example.mikailovproject.shared.finalproject.core.data.remote.AuthApi
import com.example.mikailovproject.shared.finalproject.core.data.remote.LoanApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named

@Module
class NetworkModule() {

    @Provides
    @Named("AUTH_USER")
    fun getOkHttpClientWithInterceptor(
        authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Named("AUTH_USER")
    fun getLoanApi(@Named("AUTH_USER") okHttpClient: OkHttpClient, app: Application): LoanApi =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(ResultCallAdapterFactory(app.applicationContext))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            .create(LoanApi::class.java)

    @Provides
    fun getAuthApi(app: Application): AuthApi = Retrofit.Builder().baseUrl(BASE_URL)
        .addCallAdapterFactory(ResultCallAdapterFactory(app.applicationContext))
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build().create(AuthApi::class.java)
}