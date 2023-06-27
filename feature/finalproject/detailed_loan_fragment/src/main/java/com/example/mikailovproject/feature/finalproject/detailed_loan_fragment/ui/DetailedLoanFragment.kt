package com.example.mikailovproject.feature.finalproject.detailed_loan_fragment.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mikailovproject.component.navigation.screen.navigate
import com.example.mikailovproject.feature.finalproject.detailed_loan_fragment.R
import com.example.mikailovproject.feature.finalproject.detailed_loan_fragment.databinding.FragmentDetailedLoanBinding
import com.example.mikailovproject.feature.finalproject.detailed_loan_fragment.presentation.DetailedLoanState
import com.example.mikailovproject.feature.finalproject.detailed_loan_fragment.presentation.DetailedLoanViewModel
import com.example.mikailovproject.shared.finalproject.core.presentation.ViewModelFactory
import com.example.mikailovproject.shared.finalproject.core.ui.BaseFragment
import com.example.mikailovproject.shared.finalproject.core.utils.Utils
import dagger.android.support.AndroidSupportInjection
import com.example.mikailovproject.component.navigation.R as NavR
import javax.inject.Inject

class DetailedLoanFragment :
    BaseFragment<FragmentDetailedLoanBinding>(FragmentDetailedLoanBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: DetailedLoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailedLoanViewModel::class.java]
    }

    override fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBottomNavigationView().menu.findItem(NavR.id.history).isChecked = true
        getBottomNavigationView().setOnItemSelectedListener { item ->
            when (item.itemId) {
                NavR.id.loan -> {
                    navigate(R.id.action_detailedLoanFragment_to_loanFragment)
                    true
                }
                NavR.id.history -> {
                    findNavController().popBackStack()
                    true
                }
                else -> false
            }
        }

        val id = arguments?.getString("id")
        if (id != null) {
            viewModel.getLoanById(id)
        }
        viewModel.state.observe(viewLifecycleOwner) { newState ->
            renderState(newState)
        }
    }

    private fun renderState(state: DetailedLoanState) = with(binding) {
        when (state) {
            is DetailedLoanState.Loading -> {
                progressBar.visibility = View.VISIBLE
                dismissErrorSnackBar()
            }

            is DetailedLoanState.Success -> {
                progressBar.visibility = View.GONE
                with(detailedLoanInfo) {
                    itemDate.text = Utils.convertDateTimeToReadableFormat(state.loan.date)
                    itemName.text = state.loan.firstName
                    itemLastname.text = state.loan.lastName
                    itemAmount.text = state.loan.amount.toString()
                    itemPercent.text = state.loan.percent.toString()
                    itemPeriod.text = state.loan.period.toString()
                    itemNumber.text = state.loan.phoneNumber
                    itemStatus.text = state.loan.state
                }
            }

            is DetailedLoanState.Error -> {
                progressBar.visibility = View.GONE

                state.exception.message?.let {
                    this@DetailedLoanFragment.showErrorSnackbar(it) {

                    }
                }
            }

            is DetailedLoanState.Clear -> {}
        }
    }
}