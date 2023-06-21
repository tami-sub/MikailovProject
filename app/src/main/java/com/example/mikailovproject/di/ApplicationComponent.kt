package com.example.mikailovproject.di

import com.example.mikailovproject.di.activity.ActivityModule
import com.example.mikailovproject.di.fragment.FragmentModule
import com.example.mikailovproject.network.retrofit.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, RepositoryModule::class, AppModule::class,
        ActivityModule::class, FragmentModule::class, ViewModelModule::class,
        SharedPreferencesModule::class, AndroidSupportInjectionModule::class]
)

interface ApplicationComponent : AndroidInjector<FinalProjectApp> {
    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): ApplicationComponent
    }
}