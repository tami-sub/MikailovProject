package com.example.mikailovproject.di.activity

import com.example.mikailovproject.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun injectMainActivity(): MainActivity
}