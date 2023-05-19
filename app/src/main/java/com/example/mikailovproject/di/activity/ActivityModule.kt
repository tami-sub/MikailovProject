package com.example.mikailovproject.di.activity

import com.example.mikailovproject.data.di.DataModule
import com.example.mikailovproject.di.fragment.FragmentModule
import com.example.mikailovproject.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector ()
    fun injectMainActivity(): MainActivity
}