package com.example.mikailovproject.di

import com.example.mikailovproject.data.di.DataModule
import com.example.mikailovproject.di.activity.ActivityModule
import com.example.mikailovproject.di.fragment.FragmentModule
import com.example.mikailovproject.domain.di.RepositoryModule
import com.example.mikailovproject.ui.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [DataModule::class, RepositoryModule::class,
        ActivityModule::class, FragmentModule::class,
        AndroidSupportInjectionModule::class
    ]
)


interface ApplicationComponent
    : AndroidInjector<RandomFactsApp>
{
    @Component.Factory
    interface Factory : AndroidInjector.Factory<RandomFactsApp>
}