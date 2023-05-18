package com.example.mikailovproject.di

import android.app.Application
import com.example.mikailovproject.di.DaggerApplicationComponent

class RandomFactsApp : Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}