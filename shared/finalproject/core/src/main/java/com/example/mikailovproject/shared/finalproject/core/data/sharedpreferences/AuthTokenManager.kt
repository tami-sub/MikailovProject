package com.example.mikailovproject.shared.finalproject.core.data.sharedpreferences

interface AuthTokenManager {
    fun saveAuthToken(token: String)
    fun getAuthToken(): String?
}