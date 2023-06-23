package com.example.mikailovproject.feature.finalproject.loginFragment.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mikailovproject.component.navigation.screen.navigate
import com.example.mikailovproject.feature.finalproject.loginFragment.R
import com.example.mikailovproject.feature.finalproject.loginFragment.databinding.FragmentLoginBinding
import com.example.mikailovproject.feature.finalproject.loginFragment.presentation.LoginState
import com.example.mikailovproject.feature.finalproject.loginFragment.presentation.LoginViewModel
import com.example.mikailovproject.shared.finalproject.core.presentation.ViewModelFactory
import com.example.mikailovproject.shared.finalproject.core.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: LoginViewModel

    override fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disappearBottomNavigation()
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
            registerButton.setOnClickListener {
                dismissErrorSnackBar()
                navigate(
                    R.id.action_loginFragment_to_registrationFragment,
                    com.example.mikailovproject.component.navigation.R.id.globalHost,
                )
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { newState ->
            renderState(newState)
        }
    }

    private fun renderState(state: LoginState) = with(binding) {
        when (state) {
            is LoginState.Loading -> {
                progressBar.visibility = View.VISIBLE
                dismissErrorSnackBar()
            }

            is LoginState.Success -> {
                progressBar.visibility = View.GONE
//                val id = "88005553535"
//                val request = NavDeepLinkRequest.Builder
//                    .fromUri("android-app://secondFragment?id=$id".toUri())
//                    .build()
//                findNavController().navigate(request)
                navigate(
                    R.id.action_loginFragment_to_loanFragment,
                    com.example.mikailovproject.component.navigation.R.id.globalHost,
                )
                showBottomNavigation()
                viewModel.clear()
            }

            is LoginState.Error -> {
                progressBar.visibility = View.GONE
                state.exception.message?.let {
                    this@LoginFragment.showErrorSnackbar(it) { signUp() }
                }
            }

            is LoginState.Clear -> {}
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