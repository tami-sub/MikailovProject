package com.example.mikailovproject.di.fragment

import com.example.mikailovproject.feature.finalproject.loanFragment.ui.LoanFragment
import com.example.mikailovproject.feature.finalproject.loginFragment.ui.LoginFragment
import com.example.mikailovproject.feature.finalproject.registrationFragment.ui.RegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector()
    fun injectRegistrationFragment(): RegistrationFragment

    @ContributesAndroidInjector()
    fun injectLoginFragment(): LoginFragment

    @ContributesAndroidInjector()
    fun injectLoanFragment(): LoanFragment
}