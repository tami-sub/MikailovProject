package com.example.mikailovproject.di

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RandomFactsApp : Application()
    , HasAndroidInjector
{

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    val appComponent = DaggerApplicationComponent.factory().create(this).inject(this)

    override fun androidInjector(): AndroidInjector<Any> = injector

}