package com.example.mikailovproject.di.fragment

import com.example.mikailovproject.feature.finalproject.mainFragment.ui.MainFragment
import com.example.mikailovproject.feature.finalproject.secondFragment.ui.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector()
    fun injectMainFragment(): MainFragment

    @ContributesAndroidInjector()
    fun injectSecondFragment(): SecondFragment
}