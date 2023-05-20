package com.example.mikailovproject.di

import com.example.mikailovproject.shared.randomfact.core.data.di.DataModule
import com.example.mikailovproject.di.activity.ActivityModule
import com.example.mikailovproject.di.fragment.FragmentModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [DataModule::class,
        ActivityModule::class, FragmentModule::class, AndroidSupportInjectionModule::class]
)

interface ApplicationComponent : AndroidInjector<RandomFactsApp> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<RandomFactsApp>
}