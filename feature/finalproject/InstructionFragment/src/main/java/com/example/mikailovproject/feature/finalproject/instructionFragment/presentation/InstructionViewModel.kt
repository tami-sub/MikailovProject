package com.example.mikailovproject.feature.finalproject.instructionFragment.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mikailovproject.shared.finalproject.core.sharedpreferences.AuthTokenManager
import javax.inject.Inject

class InstructionViewModel @Inject constructor(private val authTokenManager: AuthTokenManager) :
    ViewModel() {
    init {
        Log.d("joka", authTokenManager.getAuthToken() ?: "NONE")
    }
}