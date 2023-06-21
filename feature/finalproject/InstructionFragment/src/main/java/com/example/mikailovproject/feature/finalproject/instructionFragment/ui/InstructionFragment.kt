package com.example.mikailovproject.feature.finalproject.instructionFragment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mikailovproject.feature.finalproject.instructionFragment.R
import com.example.mikailovproject.feature.finalproject.instructionFragment.databinding.FragmentInstructionBinding
import com.example.mikailovproject.feature.finalproject.instructionFragment.presentation.InstructionViewModel
import com.example.mikailovproject.shared.finalproject.core.presentation.ViewModelFactory
import com.example.mikailovproject.shared.finalproject.core.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class InstructionFragment :
    BaseFragment<FragmentInstructionBinding>(FragmentInstructionBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: InstructionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[InstructionViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_instruction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }
}