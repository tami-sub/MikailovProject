package com.example.mikailovproject.feature.finalproject.history_fragment.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mikailovproject.component.navigation.screen.navigate
import com.example.mikailovproject.feature.finalproject.history_fragment.R
import com.example.mikailovproject.feature.finalproject.history_fragment.databinding.FragmentHistoryBinding
import com.example.mikailovproject.feature.finalproject.history_fragment.presentation.HistoryState
import com.example.mikailovproject.feature.finalproject.history_fragment.presentation.HistoryViewModel
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity
import com.example.mikailovproject.shared.finalproject.core.presentation.ViewModelFactory
import com.example.mikailovproject.shared.finalproject.core.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.example.mikailovproject.component.navigation.R as NavR

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]
    }

    override fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBottomNavigationView().setOnItemSelectedListener { item ->
            when (item.itemId) {
                NavR.id.loan -> {
                    findNavController().popBackStack()
                    navigate(R.id.action_loginFragment_to_loanFragment)
                    true
                }
                NavR.id.history -> {
                    true
                }
                else -> false
            }
        }
        viewModel.state.observe(viewLifecycleOwner) { newState ->
            renderState(newState)
        }
    }

    private fun renderState(state: HistoryState) = with(binding) {
        when (state) {
            is HistoryState.Loading -> {
                progressBar.visibility = View.VISIBLE
                dismissErrorSnackBar()
            }

            is HistoryState.Success -> {
                progressBar.visibility = View.GONE
                showRecyclerView(state.allLoans)

            }
            is HistoryState.Error -> {
                progressBar.visibility = View.GONE
                showRecyclerView(state.allLoans)

                state.exception.message?.let {
                    this@HistoryFragment.showErrorSnackbar(it) {
                        viewModel.getAllLoans()
                    }
                }
            }

            is HistoryState.Clear -> {}
        }
    }

    private fun showRecyclerView(data: List<LoanEntity>) {
        binding.recyclerView.apply {
            val applicationAdapter = HistoryAdapter()
            applicationAdapter.storageList = data
            adapter = applicationAdapter
        }
    }
}