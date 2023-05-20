package com.example.mikailovproject.feature.randomfact.secondFragment.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mikailovproject.feature.randomfact.secondFragment.databinding.FragmentSecondBinding
import com.example.mikailovproject.feature.randomfact.secondFragment.presentation.SecondState
import com.example.mikailovproject.feature.randomfact.secondFragment.presentation.SecondViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    @Inject
    lateinit var viewModel: SecondViewModel
    private lateinit var binding: FragmentSecondBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadButton.setOnClickListener {
            viewModel.loadStrings()
        }


        viewModel.state.observe(viewLifecycleOwner) { newState ->
            renderState(newState)
        }

    }

    private fun renderState(state: SecondState) = with(binding) {
        when (state) {
            SecondState.Loading -> {
                Toast.makeText(requireActivity(), "loading", Toast.LENGTH_SHORT).show()
                remoteText.text = ""
                localText.text = ""
            }

            is SecondState.Success -> {
                remoteText.text = state.remoteString
                localText.text = state.localString
            }
        }
    }
}