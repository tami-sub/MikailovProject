package com.example.mikailovproject.di.fragment

import com.example.mikailovproject.feature.randomfact.mainFragment.ui.MainFragment
import com.example.mikailovproject.di.RepositoryModule
import com.example.mikailovproject.feature.randomfact.secondFragment.ui.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @FragmentScope
    @ContributesAndroidInjector()
    fun injectMainFragment(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector()
    fun injectSecondFragment(): SecondFragment
}