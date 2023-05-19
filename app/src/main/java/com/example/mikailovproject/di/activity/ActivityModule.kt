package com.example.mikailovproject.di.activity

import com.example.mikailovproject.data.di.DataModule
import com.example.mikailovproject.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun injectMainActivity(): MainActivity
}