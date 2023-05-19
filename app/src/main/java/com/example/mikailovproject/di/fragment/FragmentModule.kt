package com.example.mikailovproject.di.fragment

import com.example.mikailovproject.domain.di.RepositoryModule
import com.example.mikailovproject.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [RepositoryModule::class])
    fun injectMainFragment(): MainFragment
}