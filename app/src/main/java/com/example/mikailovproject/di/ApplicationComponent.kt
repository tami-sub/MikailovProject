package com.example.mikailovproject.di

import com.example.mikailovproject.di.activity.ActivityModule
import com.example.mikailovproject.di.fragment.FragmentModule
import com.example.mikailovproject.network.retrofit.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [DataModule::class, NetworkModule::class, RepositoryModule::class,
        AppModule::class, ActivityModule::class, FragmentModule::class,
        AndroidSupportInjectionModule::class]
)

interface ApplicationComponent : AndroidInjector<RandomFactsApp> {
    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): ApplicationComponent
    }
}