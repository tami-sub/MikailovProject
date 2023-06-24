package com.example.mikailovproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mikailovproject.feature.finalproject.history_fragment.presentation.HistoryViewModel
import com.example.mikailovproject.feature.finalproject.loan_fragment.presentation.LoanViewModel
import com.example.mikailovproject.feature.finalproject.login_fragment.presentation.LoginViewModel
import com.example.mikailovproject.feature.finalproject.registration_fragment.presentation.RegistrationViewModel
import com.example.mikailovproject.shared.finalproject.core.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun registrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoanViewModel::class)
    fun loanViewModel(viewModel: LoanViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    fun historyViewModel(viewModel: HistoryViewModel): ViewModel
}