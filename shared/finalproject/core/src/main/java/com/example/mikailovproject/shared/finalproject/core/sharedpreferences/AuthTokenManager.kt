package com.example.mikailovproject.shared.finalproject.core.sharedpreferences

interface AuthTokenManager {
    fun saveAuthToken(token: String)
    fun getAuthToken(): String?
}