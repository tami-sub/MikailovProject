package com.example.mikailovproject.feature.finalproject.registrationFragment.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.mikailovproject.component.navigation.screen.navigate
import com.example.mikailovproject.feature.finalproject.registrationFragment.R
import com.example.mikailovproject.feature.finalproject.registrationFragment.databinding.FragmentRegistrationBinding
import com.example.mikailovproject.feature.finalproject.registrationFragment.presentation.RegistrationState
import com.example.mikailovproject.feature.finalproject.registrationFragment.presentation.RegistrationViewModel
import com.example.mikailovproject.shared.finalproject.core.presentation.ViewModelFactory
import com.example.mikailovproject.shared.finalproject.core.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.example.mikailovproject.component.navigation.R as NavR

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: RegistrationViewModel

    override fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            login.doAfterTextChanged {
                validateFields()
            }
            password.doAfterTextChanged {
                validateFields()
            }
            loginButton.setOnClickListener {
                signUp()
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { newState ->
            renderState(newState)
        }
    }

    private fun renderState(state: RegistrationState) = with(binding) {
        when (state) {
            is RegistrationState.Loading -> {
                progressBar.visibility = View.VISIBLE
                this@RegistrationFragment.showErrorSnackbar("", true) { }
            }

            is RegistrationState.Success -> {
                progressBar.visibility = View.GONE
                navigate(
                    R.id.action_registrationFragment_to_loginFragment,
                    NavR.id.globalHost,
                )
                viewModel.clear()
            }

            is RegistrationState.Error -> {
                progressBar.visibility = View.GONE
                state.exception.message?.let {
                    this@RegistrationFragment.showErrorSnackbar(it, false) { signUp() }
                }
            }

            is RegistrationState.Clear -> {}
        }
    }

    private fun signUp() = with(binding) {
        if (validateFields()) viewModel.signUp(login.text.toString(), password.text.toString())
    }

    private fun validateFields(): Boolean {
        with(binding) {
            val loginValid = viewModel.validateLogin(login.text.toString())
            val passwordValid = viewModel.validatePassword(password.text.toString())

            loginLayout.error = when {
                loginValid -> null
                login.text.toString().isEmpty() -> null
                else -> getString(R.string.login_error_text)
            }
            passwordLayout.error = when {
                passwordValid -> null
                password.text.toString().isEmpty() -> null
                else -> getString(R.string.password_error_text)
            }

            loginButton.isEnabled = loginValid && passwordValid
            return loginValid && passwordValid
        }
    }
}