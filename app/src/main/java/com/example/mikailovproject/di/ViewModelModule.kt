package com.example.mikailovproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mikailovproject.feature.finalproject.instructionFragment.presentation.InstructionViewModel
import com.example.mikailovproject.feature.finalproject.loginFragment.presentation.LoginViewModel
import com.example.mikailovproject.feature.finalproject.registrationFragment.presentation.RegistrationViewModel
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
    @ViewModelKey(InstructionViewModel::class)
    fun instructionViewModel(viewModel: InstructionViewModel): ViewModel
}