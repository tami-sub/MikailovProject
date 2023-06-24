package com.example.mikailovproject.shared.finalproject.core.data.sharedpreferences

import android.content.SharedPreferences
import com.example.mikailovproject.shared.finalproject.core.utils.Utils.AUTH_TOKEN
import javax.inject.Inject

class SharedPrefsAuthTokenManager @Inject constructor(private val sharedPreferences: SharedPreferences) :
    AuthTokenManager {

    override fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString(AUTH_TOKEN, token).apply()
    }

    override fun getAuthToken(): String? {
        return sharedPreferences.getString(AUTH_TOKEN, null)
    }
}