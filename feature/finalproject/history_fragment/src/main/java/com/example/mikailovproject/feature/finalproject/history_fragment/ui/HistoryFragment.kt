package com.example.mikailovproject.feature.finalproject.history_fragment.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mikailovproject.component.finalproject.navigation.screen.navigate
import com.example.mikailovproject.feature.finalproject.history_fragment.R
import com.example.mikailovproject.feature.finalproject.history_fragment.databinding.FragmentHistoryBinding
import com.example.mikailovproject.feature.finalproject.history_fragment.presentation.HistoryState
import com.example.mikailovproject.feature.finalproject.history_fragment.presentation.HistoryViewModel
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity
import com.example.mikailovproject.shared.finalproject.core.presentation.ViewModelFactory
import com.example.mikailovproject.shared.finalproject.core.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.example.mikailovproject.component.finalproject.navigation.R as NavR

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
        getBottomNavigationView().menu.findItem(NavR.id.history).isChecked = true
        getBottomNavigationView().setOnItemSelectedListener { item ->
            when (item.itemId) {
                NavR.id.loan -> {
                    navigate(R.id.action_historyFragment_to_loanFragment)
                    true
                }
                NavR.id.history -> {
                    true
                }
                else -> false
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getAllLoans()
            binding.swipeRefreshLayout.isRefreshing = false
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
                showRecyclerView(state.allLoans, true)

            }
            is HistoryState.Error -> {
                progressBar.visibility = View.GONE
                showRecyclerView(state.allLoans, false)

                state.exception.message?.let {
                    this@HistoryFragment.showErrorSnackbar(it) {
                        viewModel.getAllLoans()
                    }
                }
            }

            is HistoryState.Clear -> {}
        }
    }

    private fun showRecyclerView(data: List<LoanEntity>, isRemote: Boolean) {
        binding.recyclerView.apply {
            val applicationAdapter = HistoryAdapter(findNavController(), isRemote)
            applicationAdapter.storageList = data
            adapter = applicationAdapter
        }
    }
}