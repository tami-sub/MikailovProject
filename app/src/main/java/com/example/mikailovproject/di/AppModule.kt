package com.example.mikailovproject.di

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: RandomFactsApp) {

    @Provides
    @AppScope
    fun provideApplication(): RandomFactsApp = application
}