package com.example.mikailovproject.di.fragment

import com.example.mikailovproject.feature.randomfact.mainFragment.ui.MainFragment
import com.example.mikailovproject.di.RepositoryModule
import com.example.mikailovproject.feature.randomfact.secondFragment.ui.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [RepositoryModule::class])
    fun injectMainFragment(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RepositoryModule::class])
    fun injectSecondFragment(): SecondFragment
}