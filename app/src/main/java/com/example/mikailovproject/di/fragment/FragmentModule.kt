package com.example.mikailovproject.di.fragment

import com.example.mikailovproject.feature.finalproject.detailed_loan_fragment.ui.DetailedLoanFragment
import com.example.mikailovproject.feature.finalproject.history_fragment.ui.HistoryFragment
import com.example.mikailovproject.feature.finalproject.loan_fragment.ui.LoanFragment
import com.example.mikailovproject.feature.finalproject.login_fragment.ui.LoginFragment
import com.example.mikailovproject.feature.finalproject.registration_fragment.ui.RegistrationFragment
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

    @ContributesAndroidInjector()
    fun injectHistoryFragment(): HistoryFragment

    @ContributesAndroidInjector()
    fun injectDetailedLoanFragment(): DetailedLoanFragment
}