package com.example.mikailovproject.di

import com.example.mikailovproject.data.di.DataModule
import com.example.mikailovproject.domain.di.RepositoryModule
import com.example.mikailovproject.ui.MainFragment
import dagger.Component

@AppScope
@Component(modules = [DataModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun inject(fragment: MainFragment)
}