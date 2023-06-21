package com.example.mikailovproject.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.mikailovproject.shared.finalproject.core.sharedpreferences.AuthTokenManager
import com.example.mikailovproject.shared.finalproject.core.sharedpreferences.SharedPrefsAuthTokenManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.applicationContext.getSharedPreferences(
            "my_app_shared_prefs", Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideAuthTokenManager(sharedPreferences: SharedPreferences): AuthTokenManager {
        return SharedPrefsAuthTokenManager(sharedPreferences)
    }
}