package com.example.mikailovproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mikailovproject.databinding.FragmentMainBinding
import com.example.mikailovproject.di.RandomFactsApp
import com.example.mikailovproject.presentation.MainState
import com.example.mikailovproject.presentation.MainViewModel
import javax.inject.Inject


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as RandomFactsApp)
            .appComponent
            .inject(this)
    }

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
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

    private fun renderState(state: MainState) = with(binding) {
        when (state) {
            MainState.Loading -> {
                Toast.makeText(requireActivity(), "loading", Toast.LENGTH_SHORT).show()
                remoteText.text = ""
                localText.text = ""
            }

            is MainState.Success -> {
                remoteText.text = state.remoteString
                localText.text = state.localString
            }
        }
    }
}