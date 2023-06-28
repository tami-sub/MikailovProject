package com.example.mikailovproject.feature.finalproject.loan_fragment.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.mikailovproject.component.navigation.screen.navigate
import com.example.mikailovproject.feature.finalproject.loan_fragment.R
import com.example.mikailovproject.feature.finalproject.loan_fragment.databinding.FragmentLoanBinding
import com.example.mikailovproject.feature.finalproject.loan_fragment.presentation.LoanState
import com.example.mikailovproject.feature.finalproject.loan_fragment.presentation.LoanViewModel
import com.example.mikailovproject.shared.finalproject.core.presentation.ViewModelFactory
import com.example.mikailovproject.shared.finalproject.core.ui.BaseFragment
import com.example.mikailovproject.shared.finalproject.core.utils.Utils.convertDateTimeToReadableFormat
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.example.mikailovproject.component.navigation.R as NavR

class LoanFragment : BaseFragment<FragmentLoanBinding>(FragmentLoanBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: LoanViewModel
    private var maxAmountValue: Long = 0
    private var percentValue: Double = 0.0
    private var periodValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoanViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBottomNavigationView().menu.findItem(NavR.id.loan).isChecked = true
        with(binding) {
            applyLoanButton.setOnClickListener {
                hideKeyboard()
                createLoan()
            }
        }
        getBottomNavigationView().setOnItemSelectedListener { item ->
            when (item.itemId) {
                NavR.id.loan -> {
                    true
                }
                NavR.id.history -> {
                    navigate(
                        R.id.action_loanFragment_to_historyFragment,
                        com.example.mikailovproject.component.navigation.R.id.globalHost,
                    )
                    true
                }
                else -> false
            }
        }
        viewModel.state.observe(viewLifecycleOwner) { newState ->
            renderState(newState)
        }
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigation()
    }

    override fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }

    private fun renderState(state: LoanState) = with(binding) {
        when (state) {
            is LoanState.Loading -> {
                progressBar.visibility = View.VISIBLE
                dismissErrorSnackBar()
            }

            is LoanState.SuccessConditions -> {
                progressBar.visibility = View.GONE
                amountLayout.hint = getString(R.string.max_amount, state.maxAmount.toString())
                percent.setText(getString(R.string.percent, state.percent.toString()))
                period.setText(getString(R.string.period, state.period.toString()))
                maxAmountValue = state.maxAmount
                percentValue = state.percent
                periodValue = state.period
                with(binding) {
                    name.doAfterTextChanged {
                        validateFields()
                    }
                    lastname.doAfterTextChanged {
                        validateFields()
                    }
                    amount.doAfterTextChanged {
                        validateFields()
                    }
                    number.doAfterTextChanged {
                        validateFields()
                    }
                }
            }

            is LoanState.SuccessCreation -> {
                progressBar.visibility = View.GONE
                nameLayout.visibility = View.GONE
                lastnameLayout.visibility = View.GONE
                amountLayout.visibility = View.GONE
                percentLayout.visibility = View.GONE
                periodLayout.visibility = View.GONE
                numberLayout.visibility = View.GONE
                applyLoanButton.visibility = View.GONE
                with(itemLoan) {
                    itemDate.text = convertDateTimeToReadableFormat(state.loan.date)
                    itemName.text = state.loan.firstName
                    itemLastname.text = state.loan.lastName
                    itemAmount.text = state.loan.amount.toString()
                    itemPercent.text = state.loan.percent.toString()
                    itemPeriod.text = state.loan.period.toString()
                    itemNumber.text = state.loan.phoneNumber
                    itemStatus.text = state.loan.state
                }
                itemLoan.root.visibility = View.VISIBLE
                doneImage.visibility = View.VISIBLE
            }

            is LoanState.Error -> {
                progressBar.visibility = View.GONE
                state.exception.message?.let {
                    this@LoanFragment.showErrorSnackbar(it) {
                        if (state.isConditions) {
                            viewModel.getLoanConditions()
                        } else {
                            createLoan()
                        }
                    }
                }
            }

            is LoanState.Clear -> {}
        }
    }

    private fun createLoan() = with(binding) {
        if (validateFields()) {
            viewModel.createLoan(
                amount = amount.text.toString().trim().toDouble(),
                firstName = name.text.toString(),
                lastName = lastname.text.toString(),
                percent = percentValue,
                period = periodValue,
                phoneNumber = number.text.toString()
            )
        }
    }

    private fun validateFields(): Boolean {
        with(binding) {
            val nameValid = viewModel.validateName(name.text.toString())
            val lastnameValid = viewModel.validateName(lastname.text.toString())
            val amountValid = viewModel.validateAmount(amount.text.toString(), maxAmountValue)
            val phoneNumberValid = viewModel.validatePhoneNumber(number.text.toString())

            nameLayout.error = when {
                nameValid -> null
                name.text.toString().isEmpty() -> null
                else -> getString(R.string.name_error_text)
            }

            lastnameLayout.error = when {
                lastnameValid -> null
                lastname.text.toString().isEmpty() -> null
                else -> getString(R.string.name_error_text)
            }

            amountLayout.error = when {
                amountValid -> null
                amount.text.toString().isEmpty() -> null
                else -> getString(R.string.hollow_space)
            }

            numberLayout.error = when {
                phoneNumberValid -> null
                number.text.toString().isEmpty() -> null
                else -> getString(R.string.incorrect_phone_number)
            }

            applyLoanButton.isEnabled =
                nameValid && lastnameValid && amountValid && phoneNumberValid
            return nameValid && lastnameValid && amountValid && phoneNumberValid
        }
    }
}